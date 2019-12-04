package com.ifmo.lesson21;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Counter {
    private static final int THREAD_COUNT = 10;

    private static class FileIterator implements Iterator, AutoCloseable {
        private final BufferedReader br;
        private String nextLine;

        public FileIterator(File fileName) throws IOException {
            br = new BufferedReader(new FileReader(fileName));
            nextLine = br.readLine();
        }

        @Override
        public boolean hasNext() {
            return nextLine != null;
        }

        @Override
        public String next() {
            String lineToReturn = nextLine;
            try {
                nextLine = br.readLine();
            } catch (IOException e) {
                nextLine = null;
            }
            return lineToReturn;
        }

        @Override
        public void remove() {
            throw new NotImplementedException();
        }

        @Override
        public void close() throws IOException {
            br.close();
        }
    }

    private static class Transformers {
        public String[] mapToTokens(String input) {
            return input.split("[ “”\" _\\.,\\-\\+]");
        }

        private String[] filterIllegalTokens(String[] words) {
            List<String> filteredList = new ArrayList<>();
            for (String word : words) {
                if (word.matches("[a-zA-Z]+")) {
                    filteredList.add(word);
                }
            }
            return filteredList.toArray(new String[0]);
        }

        private String[] mapToLowerCase(String[] words) {
            String[] filteredList = new String[words.length];
            for (int i = 0; i < words.length; i++) {
                filteredList[i] = words[i].toLowerCase();
            }
            return filteredList;
        }

        public synchronized void reduce(Map<String, Integer> counter, String word) {
            if (counter.containsKey(word)) {
                counter.put(word, counter.get(word) + 1);
            } else {
                counter.put(word, 1);
            }
        }
    }

    private static class TransformationThread implements Runnable {
        private Transformers tr;
        private Queue<String> dataQueue;
        private Map<String, Integer> counters;

        public TransformationThread(Transformers tr, Map<String, Integer> counters, Queue<String> dataQueue) {
            this.tr = tr;
            this.dataQueue = dataQueue;
            this.counters = counters;
        }

        @Override
        public void run() {
            while (!dataQueue.isEmpty()) {
                String line = dataQueue.poll();
                if (line != null) {
                    String[] words = tr.mapToTokens(line);
                    String[] legalWords = tr.filterIllegalTokens(words);
                    String[] lowerCaseWords = tr.mapToLowerCase(legalWords);
                    for (String word : lowerCaseWords) {
                        tr.reduce(counters, word);
                    }
                }
            }
        }
    }

    public static void main(final String[] args) throws Exception {
        Transformers tr = new Transformers();
        String path = System.getProperty("user.dir") + "\\src\\main\\java\\com\\ifmo\\lesson21\\wap.txt";
        File inputFile = new File(path);
        Map<String, Integer> counters = new HashMap<>();
        final Queue<String> dataQueue = new ConcurrentLinkedQueue<>();
        new Thread(() -> {
            try (FileIterator fc = new FileIterator(inputFile)) {
                while (fc.hasNext()) {
                    dataQueue.add(fc.next());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        while (dataQueue.isEmpty()) {
            Thread.sleep(5);
        }
        ExecutorService es = Executors.newFixedThreadPool(THREAD_COUNT);
        for (int i = 0; i < THREAD_COUNT; i++) {
            es.execute(new TransformationThread(tr, counters, dataQueue));
        }
        es.shutdown();
        es.awaitTermination(1, TimeUnit.MINUTES);

        counters.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(System.out::println);
    }
}