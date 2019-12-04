package com.ifmo.lesson21;

public class Waiter implements Runnable {

    private Cafe r;

    private void makeServe() throws InterruptedException {
        synchronized (r) {
            r.notifyAll();
            while (!r.orderMadeByClient)
                r.wait();
            System.out.println("Start serving order ");
            Thread.sleep(1000);
            r.putOrder(r.getOrderMadeByClient());
            while (!r.orderReady)
                r.wait();
            r.putOrderReceived(r.getFood());
            System.out.println("End serving order ");
        }
    }


    Waiter(Cafe r) {
        this.r = r;
    }

    @Override
    public void run() {
        try {
            makeServe();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}