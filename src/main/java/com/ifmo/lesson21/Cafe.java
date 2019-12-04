package com.ifmo.lesson21;

class Cafe {
    boolean orderMadeByClient;
    boolean orderTaken;
    boolean orderReady;
    boolean orderReceived;
    long orderId;

    Cafe() {
    }

    void makeRestaurant() {
        Client cl = new Client(this);
        Chef ch = new Chef(this);
        Waiter w = new Waiter(this);
        Thread t1 = new Thread(cl);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(ch);
        t1.start();
        t2.start();
        t3.start();
    }

    synchronized long getOrderMadeByClient() {
        while (!orderMadeByClient) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        orderMadeByClient = false;
        notifyAll();
        System.out.println("get order from client #" + orderId);
        return orderId;
    }

    synchronized void putOrderMadeByClient(long l) {
        while (orderMadeByClient) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        orderMadeByClient = true;
        this.orderId = l;
        notifyAll();
        System.out.println("put order client #" + l);
    }

    synchronized long getOrder() {
        while (!orderTaken) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        orderTaken = false;
        notifyAll();
        System.out.println("get order from waiter #" + orderId);
        return orderId;
    }

    synchronized void putOrder(long l) {
        while (orderTaken) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        orderTaken = true;
        this.orderId = l;
        notifyAll();
        System.out.println("put order to cook #" + l);
    }

    synchronized long getFood() {
        while (!orderReady) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        orderReady = false;
        notifyAll();
        System.out.println("get order by waiter #" + orderId);
        return orderId;
    }

    synchronized void putFood(long n) {
        while (orderReady) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        orderReady = true;
        this.orderId = n;
        notifyAll();
        System.out.println("put order by cook #" + n);
    }

    synchronized void getOrderReceived() {
        while (!orderReceived) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        orderReceived = false;
        notifyAll();
        System.out.println("get order by client #" + orderId);
    }

    synchronized void putOrderReceived(long l) {
        while (orderReceived) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        orderReceived = true;
        this.orderId = l;
        notifyAll();
        System.out.println("put order by waiter #" + l);
    }
}