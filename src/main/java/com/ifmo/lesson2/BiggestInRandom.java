package com.ifmo.lesson2;

import java.util.*;

public class BiggestInRandom {
    /*
     Создать программу, выводящую на экран случайно сгенерированное трёхзначное
     натуральное число и его наибольшую цифру.Примеры работы программы:
     В числе 208 наибольшая цифра 8.
     В числе 774 наибольшая цифра 7.
     В числе 613 наибольшая цифра 6.
     */
    public static void main(String[] args) {
        int rnd = threeDigitRandom();
        String largestDigit = largestDigit(rnd);
        System.out.println(largestDigit);
    }

    public static int threeDigitRandom() {
        // TODO implement
        double numb = Math.abs(Math.random() * (999));
        return (int) numb;
    }

    public static String largestDigit(int rnd) {
        // TODO implement
        String[] convert = String.valueOf(rnd).split("");
        ArrayList list = new ArrayList<>(Arrays.asList(convert));
        return "В числе " + rnd + " наибольшая цифра " + Collections.max(list) + ".";
    }
}
