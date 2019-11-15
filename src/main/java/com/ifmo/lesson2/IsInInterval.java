package com.ifmo.lesson2;

import java.util.Random;

public class IsInInterval {
    /*
     Создать программу, которая будет проверять попало ли случайно выбранное из отрезка
    [5;155] целое число в интервал (25;100) и сообщать результат на экран.Примеры работы
    программы:
    Число 113 не содержится в интервале (25,100) Число 72 содержится в интервале (25,100) 
    Число 25 не содержится в интервале (25,100) Число 155 не содержится в интервале (25,100) 
     */
    public static void main(String[] args) {
        int rnd = randomInt();

        String inInterval = isInInterval(rnd);

        // TODO implement
        System.out.println(inInterval);
    }

    public static int randomInt() {
        // TODO implement
        int diap1 = 5;
        int diap2 = 155;
        double numb = diap1 + (int) (Math.random() * ((diap2 - diap1) + 1));
        return (int) numb;
    }

    public static String isInInterval(int rnd) {
        // TODO implement
        System.out.println(rnd);
        int findDiap1 = 25;
        int findDiap2 = 100;
        if ((findDiap1 < rnd) && (rnd < findDiap2)) {
            System.out.println("Число " + rnd + " содержится в интервале [25, 100]");
        } else
            System.out.println(" Число + " + rnd + " не содержится в интервале [25, 100]");
        return "";
    }
}
