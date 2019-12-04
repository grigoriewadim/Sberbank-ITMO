package com.ifmo.lesson20.chat.example;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread[] threadLine = new Thread[10];
        for (int i = 0; i < threadLine.length; i++) {
            threadLine[i] = new Thread(new HelloThread());
        }
        startThread(threadLine);
    }

    private static void startThread(Thread[] threads) throws InterruptedException {
        for (Thread thread : threads) {
            thread.start();
            if (thread.isAlive()) {
                thread.join();
                System.out.println(" I've joined in " + thread.getName());
            }
        }
    }

    public static class HelloThread implements Runnable {
        @Override
        public void run() {
            System.out.printf("Hello, multithread world! (%s)\n", Thread.currentThread().getName());
        }
    }
}
