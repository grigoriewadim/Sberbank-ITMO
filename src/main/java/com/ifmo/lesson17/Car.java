package com.ifmo.lesson17;

class Car {
    Car(CarModels model) {
        this.model = model;
    }
    private CarModels model = null;
    @Override
    public String toString() {
        return "Model - " + model;
    }
}
