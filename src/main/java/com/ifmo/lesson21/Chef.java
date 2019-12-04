package com.ifmo.lesson21;

public class Chef implements Runnable {

    private Cafe r;

    private void makeFood() throws InterruptedException {
        synchronized (r) {
            r.notifyAll();
            while (!r.orderTaken)
                r.wait();
            System.out.println("Start making food ");
            Thread.sleep(1000);
            r.putFood(r.getOrder());
            System.out.println("End making food ");
        }
    }

    Chef(Cafe r) {
        this.r = r;
    }

    @Override
    public void run() {
        try {
            makeFood();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}