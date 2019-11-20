//package com.ifmo.lesson11;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.util.*;
//
//public class TopWords {
//    private interface GeneratorMap<K, V> {
//        Map<K, V> generate(List<K> list);
//    }
//
//    public static void main(String[] args) throws IOException {
//        // Создаем файл, указывая путь к текстовому файлу на диске
//        File text = new File(System.getProperty("user.dir") + "/src/main/resources/wap.txt"));
//        // Вычитываем все строки из файла
//        List<String> lines = Files.readAllLines(text.toPath());
//        // Создаем пустую коллекцию для слов.
//        List<String> words = new ArrayList<>();
//        for (String line : lines) {
//            // Для каждой строки
//            String[] wordSplit =
//                    line.toLowerCase() // Переводим в нижний регистр
//                            .replaceAll("\\p{Punct}", " ") // Заменяем все знаки на пробел
//                            .trim() // Убираем пробелы в начале и конце строки.
//                            .split("\\s"); // Разбиваем строки на слова
//
//            for (String s : wordSplit) {
//                // Выбираем только непустые слова.
//                if (s.length() > 0)
//                    words.add(s.trim());
//            }
//        }
//        System.out.println(top10Words(words));
//        System.out.println(top10Phrases(words));
//        System.out.println(charactersFrequency(words));
//    }
//
//    public static Map<String, Integer> top10Words(List<String> words) {
//        // todo implement
//        return getTop(lines, 10, TopWords::initialWordMap, TopWords::compare);
//    }
//
//    public static Map<String, Integer> top10Phrases(List<String> words) {
//        // todo implement
//        return getTop(lines, 10, TopWords::initialPhrasesMap, TopWords::compare);
//    }
//
//    public static Map<Character, Integer> charactersFrequency(List<String> words) {
//        // todo implement
//        return initialCharacterMap(lines);
//    }
//
//    private static Map<String, Integer> initialPhrasesMap(List<String> words) {
//        Map<String, Integer> map = new HashMap<>();
//
//        for (int i = 1; i < words.size(); i++) {
//            map.merge(words.get(i - 1) + " " + words.get(i), 0, (oldValue, newValue) -> ++oldValue);
//        }
//
//        return map;
//    }
//
//    private static Map<String, Integer> initialWordMap(List<String> words) {
//        Map<String, Integer> map = new HashMap<>();
//        for (String word : words) {
//            map.merge(word, 0, (oldValue, value) -> ++oldValue);
//        }
//        return map;
//    }
//
//    private static Map<Character, Integer> initialCharacterMap(List<String> words) {
//        Map<Character, Integer> map = new HashMap<>();
//        for(String word: words) {
//            for (int i = 0; i < word.length(); i++) {
//                map.merge(word.charAt(i), 0, (oldValue, newValue) -> ++oldValue);
//            }
//        }
//
//        return map;
//    }
//}
