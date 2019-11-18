package com.ifmo.lesson3;

public class UnevenArray {
    /*
    Создайте массив из всех нечётных чисел от 1 до 99, выведите его на экран в строку, а затем
    этот же массив выведите на экран тоже в строку, но в обратном порядке (99 97 95 93 … 7 5 3
    1)
     */
    private static int[] array = new int[50];

    private static int[] create() {
        for (int i = 0; i < array.length; i++) {
            array[i] = 2 * i + 1; //чтобы не было нуля
        }
        return array;
    }

    private static int[] reverse() {
        int[] reversed = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            reversed[i] = array[array.length - 1 - i];
        }
        return reversed;
    }

    public static void main(String[] args) {
        create();
        for (int value : array) {
            System.out.print(value + " ");
        }
        for(int reverseValue: reverse()) {
            System.out.print(reverseValue + " ");
        }
    }
}
