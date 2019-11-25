package com.ifmo.lesson18.sensor;

import java.util.Observable;
import java.util.Observer;

class Second implements Observer {
    public void update(Observable obj, Object arg) {
        if (((int) arg) >= 80  && (int) arg <= 109)
            System.out.println("Сработал желтый датчик! -> "+(int)arg);
    }
}
