package com.ifmo.lesson24;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;


public class Bank {
    static Logger logger;
    private Map<Long, User> users = new ConcurrentHashMap<>();
    private List<Account> accounts = new CopyOnWriteArrayList<>();

    private static class User {
        private final long id;
        private final String name;

        private User(long id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    private static class Account {
        private final long id;
        private final long userId;
        private long amount;

        private Account(long id, long userId, long amount) {
            this.id = id;
            this.userId = userId;
            this.amount = amount;
        }
    }

    private static class Transaction {
        private final BigInteger transactionId;
        private final long fromAccountId;
        private final long toAccountId;
        private final long amount;
        private final boolean success;

        private Transaction(long fromAccountId, long toAccountId, long amount, boolean success) {
            this.success = success;
            this.transactionId = new BigInteger("" + System.currentTimeMillis() + fromAccountId + toAccountId + amount);
            this.fromAccountId = fromAccountId;
            this.toAccountId = toAccountId;
            this.amount = amount;
        }

        @Override
        public String toString() {
            return "Transaction{" +
                    "transactionId=" + transactionId +
                    ", fromAccountId=" + fromAccountId +
                    ", toAccountId=" + toAccountId +
                    ", amount=" + amount +
                    ", success=" + success +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        // 1. Сгенерируйте пользователей и их аккаунты (все идентификаторы должны быть уникальны).
        // 2. Переводите деньги со случайного аккаунта на случайный в 100 потоках.
        // Другими словами, создайте 100 потоков или пул из 100 потоков, в которых
        // выполните перевод вызовом метода transferMoney().
        Bank bank = new Bank();
        Random random = new Random();
        for (int i = 1; i < 50; i++) {
            long randomize = random.nextInt(300);
            bank.accounts.add(new Account(i, i, randomize));
            bank.users.put(randomize + i, new User(i + 100, "User" + i));
        }
//        CopyOnWriteArrayList cowa = new CopyOnWriteArrayList();
//        cowa.iterator();
        for (int j = 0; j < 100; j++) {
            int randomAcc1 = random.nextInt(99);
            int randomAcc2 = random.nextInt(randomAcc1);
            Runnable r = () -> {
                try {
                    transferMoney(bank.accounts.get(randomAcc1), bank.accounts.get(randomAcc2), random.nextInt(500));
//                        Thread.sleep(10);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
            Thread exec = new Thread(r);
            exec.start();
        }
    }

    // TODO Самая главная часть работы!
    public static void transferMoney(Account from, Account to, long amount) throws Exception {
        // 1. Атомарно и потокобезопасно перевести деньги в количестве 'amount' со счёта 'from' на счёт 'to'.
        // 2. Создать объект Transaction, содержащий информацию об операции и отправить в очередь
        // потоку Logger, который проснётся и напечатает её.
        Logger logger = Logger.getLogger(Bank.class.getName());
        AtomicLong longAmount = new AtomicLong();
        if (from.amount >= amount) {
            synchronized (from) {
                synchronized (to) {
                    Transaction transaction = new Transaction(from.id, to.id, longAmount.addAndGet(amount), true);
                    logger.info(transaction.toString());
                }
            }
            logger.info("Перевод выполнен ");
        } else {
            logger.warning("Не достаточно денег ");
        }
    }
}
