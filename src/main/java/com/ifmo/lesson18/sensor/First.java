package com.ifmo.lesson18.sensor;

import java.util.Observable;
import java.util.Observer;

class First implements Observer {
    public void update(Observable obj, Object arg) {
        if (((int) arg) >= 50  && (int) arg <= 79)
        System.out.println("Сработал зеленый датчик! -> "+(int)arg);
    }
}
