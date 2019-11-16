package com.ifmo.lesson3;

import java.util.Arrays;
import java.util.Collections;

public class UnevenArray {
    /*
    Создайте массив из всех нечётных чисел от 1 до 99, выведите его на экран в строку, а затем
    этот же массив выведите на экран тоже в строку, но в обратном порядке (99 97 95 93 … 7 5 3
    1)
     */
    private static int[] array = new int[100];

    private static int[] create() {
        for (int i = 1; i < 100; i++) {
            if (i % 2 != 0) {
                array[i] = i;
            }
        }
        return array;
    }

    private static int[]reverse() {
        int l = array.length;
        for (int j = 0; j < l / 2; j++) {
            int temp = array[j];
            array[j] = array[l - j - 1];
            array[l - j - 1] = temp;
        }
        return array;
    }

    private static void summArray(int[] array) {
        create();
        int[] newArray = new int[200];
        newArray = Arrays.copyOf(array, array.length);


    }

    public static void main(String[] args) {
//        summArray();
    }
}
