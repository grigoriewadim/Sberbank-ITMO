//package com.ifmo.lesson15;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//import java.util.stream.Collectors;
//
///**
// * Реализация входящего потока, котрая генерирует данные в виде пилы
// * указанной длины и аплитуды.
// * Например:
// * Амплитуда 5, тогда вывод будет:
// * 0, 1, 2, 3, 4, 0, 1, 2, 3, 4, 0...
// */
//public class SawInputStream extends InputStream {
//    private final int amplitude;
//    private final long length;
//
//    public SawInputStream(int amplitude, long length) {
//        this.amplitude = amplitude;
//        this.length = length;
//    }
//
//    @Override
//    public int read() throws IOException {
//        // TODO implement
//        List<Integer> src = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            src.add(i*10);
//        }
//        Random r = new Random();
//        List<Integer> toSort = new ArrayList<>();
//        toSort.addAll(src);
//        toSort.sort((o1, o2) -> {
//            if (o1.equals(o2)) return 0;
//            return (r.nextBoolean()) ? 1 : -1;
//        });
//        List<Integer> a = new ArrayList<>();
//        long limit = 10;
//        for (Integer s : toSort) {
//            if (limit-- == 0) break;
//            a.add(s);
//        }
////        return a;
//    }
//}
