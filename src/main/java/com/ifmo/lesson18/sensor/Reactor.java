package com.ifmo.lesson18.sensor;

import java.util.Observable;

class Reactor extends Observable {
    void reactor() {
        int[] reactorTemp = new int[200];
        for (int i = 0; i < 200; i++) {
            reactorTemp[i] = i;
        }
        for(int temp: reactorTemp){
            setChanged();
            notifyObservers(temp);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("Found error!");
            }
        }
    }
}
