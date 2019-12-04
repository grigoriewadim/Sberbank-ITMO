package com.ifmo.lesson17;

class CarFactory {
    private CarFactory() {
    }

    ;

    static Car factory(Location location) {
        Car car = null;
        if (location == (Location.GERMANY)) {
            car = new Car(CarModels.BMW);
        }
        if (location == (Location.KOREA)) {
            car = new Car(CarModels.KIA);
        }
        if (location == (Location.JAPAN)) {
            car = new Car(CarModels.TOYOTA);
        }
        if (location == (Location.RUSSIA)) {
            car = new Car(CarModels.LADA);
        }
        return car;
    }
}