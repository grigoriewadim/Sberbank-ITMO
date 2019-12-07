package com.ifmo.lesson24;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

public class Bank {
    private Map<Long, User> users = new ConcurrentHashMap<>();
    private List<Account> accounts = new CopyOnWriteArrayList<>();

    private class User {
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

    public Bank(Map<Long, User> users, List<Account> accounts) {
        this.users = users;
        this.accounts = accounts;
    }

    public static class Transfer implements Runnable {
        @Override
        public void run() {
            Thread transThread = new Thread();
            Account acc1 = new Account(100, 100, 100);
            Account acc2 = new Account(200,200,200);
            try {
                transferMoney(acc1, acc2, 100);
                transThread.start();
                transThread.wait(100);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static synchronized void main(String[] args) throws Exception {
        // 1. Сгенерируйте пользователей и их аккаунты (все идентификаторы должны быть уникальны).
        // 2. Переводите деньги со случайного аккаунта на случайный в 100 потоках.
        // Другими словами, создайте 100 потоков или пул из 100 потоков, в которых
        // выполните перевод вызовом метода transferMoney().
        for (int i = 0; i < 100; i++) {
            new Transfer();
        }
    }

    // TODO Самая главная часть работы!
    public static void transferMoney(Account from, Account to, long amount) throws Exception {
        // 1. Атомарно и потокобезопасно перевести деньги в количестве 'amount' со счёта 'from' на счёт 'to'.
        // 2. Создать объект Transaction, содержащий информацию об операции и отправить в очередь
        // потоку Logger, который проснётся и напечатает её.
        synchronized (from) {
            synchronized (to) {
                if (from.amount < 0) {
                    throw new Exception();
                }
                else {
                    to.amount = from.amount;
                    Transaction transaction = new Transaction(from.amount, to.amount, amount, true);
                    Logger logger = Logger.getLogger(Bank.class.getName());
                    logger.info(transaction.fromAccountId + transaction.toAccountId + transaction.amount+"");
                    System.out.println(to.amount);
                }
            }
        }
    }
}
