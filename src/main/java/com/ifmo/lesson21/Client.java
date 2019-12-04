package com.ifmo.lesson21;

public class Client implements Runnable {
    private Cafe r;

    private void makeOrder() throws InterruptedException {
        synchronized (r) {
            r.notifyAll();
            System.out.println("Start ordering ");
            Thread.sleep(1000);
            r.putOrderMadeByClient(r.orderId);
            System.out.println("End ordering ");
            while(!r.orderReceived)
                r.wait();
            r.getOrderReceived();
        }
    }

    Client(Cafe r) {
        this.r=r;
    }

    public void run() {
        try {
            makeOrder();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
