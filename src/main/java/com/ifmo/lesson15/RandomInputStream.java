package com.ifmo.lesson15;

import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Реализация потока ввода, которая генерирует случайные данные
 * указанной длины.
 */
public class RandomInputStream extends InputStream {
    private final Random random;
    private final long length;

    public RandomInputStream(Random random, long length) {
        this.random = random;
        this.length = length;
    }

    @Override
    public int read() throws IOException {
        // TODO implement
        int index = 0;
        RandomInputStream stream =
                new RandomInputStream(random, length);
        ArrayList<Integer> arrayList = new ArrayList<>();
        int readBytes;
        while((readBytes = stream.read()) > 0) {
            arrayList.add(readBytes);
            index++;
        }
        return arrayList.get(index);
    }
}
