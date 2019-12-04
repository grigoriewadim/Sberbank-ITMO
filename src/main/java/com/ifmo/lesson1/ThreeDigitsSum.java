package com.ifmo.lesson1;

public class ThreeDigitsSum {
    /*
    В переменной n хранится натуральное трёхзначное число.
    Создайте программу, вычисляющую и выводящую на экран сумму цифр числа n.
     */
    public static void main(String[] args) {
        int n = 123;
        int sum = sum(n);
        System.out.println(sum);
    }

    public static int sum(int n) {
        // TODO implement
        int result = 0;
        String[] convert = String.valueOf(n).split("");
        for (int i = 0; i < convert.length; i++) {
            result = result + Integer.parseInt(convert[i]);
        }
        return result;
    }
}
