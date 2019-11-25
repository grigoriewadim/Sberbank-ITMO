package com.ifmo.lesson18.sensor;

import java.util.Observable;
import java.util.Observer;

public class Third implements Observer {
    public void update(Observable obj, Object arg) {
        if (((int) arg) >= 110  && (int) arg <= 180)
            System.out.println("Сработал красный датчик! -> "+(int)arg);
    }
}
