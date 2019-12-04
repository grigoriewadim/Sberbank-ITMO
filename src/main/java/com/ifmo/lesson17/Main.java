package com.ifmo.lesson17;

public class Main {
    public static void main(String[] args) {

        System.out.println(CarFactory.factory(Location.GERMANY));
        System.out.println(CarFactory.factory(Location.JAPAN));
        System.out.println(CarFactory.factory(Location.RUSSIA));
        System.out.println(CarFactory.factory(Location.KOREA));
    }

}
