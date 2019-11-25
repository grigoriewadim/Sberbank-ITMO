package com.ifmo.lesson18.sensor;

class Main {
    public static void main(String args[]) {
        Reactor reactorSensor = new Reactor();
        First greenSensor = new First();
        Second yellowSensor = new Second();
        Third redSensor = new Third();
        reactorSensor.addObserver(greenSensor);
        reactorSensor.addObserver(yellowSensor);
        reactorSensor.addObserver(redSensor);
        reactorSensor.reactor();

    }
}
