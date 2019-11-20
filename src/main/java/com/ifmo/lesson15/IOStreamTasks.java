package com.ifmo.lesson15;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static javax.swing.UIManager.getString;

/*
    Реализуйте все методы с использованием потоков ввода-вывода.
 */
public class IOStreamTasks {
    private static byte[] buffer = new byte[2048];
    private static String pathName = System.getProperty("user.dir");

    public static void main(String[] args) {
        File src = new File("D:\\!JAVA!\\Projects\\Sberbank-ITMO-master\\src\\main\\resources\\wap.txt");
        File dst = new File("D:\\!JAVA!\\Projects\\Sberbank-ITMO-master\\src\\main\\resources\\wap_copy.txt");
        File dec = new File(pathName + "\\out\\" + "wap_decrypte.txt");
        try (InputStream in = new FileInputStream(src);
             OutputStream out = new FileOutputStream(dst);
             OutputStream decrypt = new FileOutputStream(dec)) {
            copy(in, out);
            assembly(split(dst, new File("wap_part.txt"), 102400), new File("fullFile.txt"));
            encrypt(in, decrypt, "12345");
            encrypt(src, dec, new File(pathName + "\\out\\key.file"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Полностью копирует один поток в другой.
     *
     * @param src Входящий поток.
     * @param dst Выходящий поток.
     * @throws IOException Будет выброшен в случае ошибки.
     */
    public static void copy(InputStream src, OutputStream dst) throws IOException {
        // TODO implement
        int len = 0;
        while ((len = src.read(buffer)) > 0) {
            dst.write(buffer, 0, len);
        }
    }

    /**
     * Последовательно разбивает файл на несколько более мелких, равных
     * указанному размеру. Последний файл может быть меньше задданого
     * размера.
     *
     * @param file   Файл, который будет разбит на несколько.
     * @param dstDir Директория, в которой будут созданы файлы меньшего размера.
     * @param size   Размер каждого файла-части, который будет создан.
     * @return Список файлов-частей в том порядке, в котором они должны считываться.
     * @throws IOException Будет выброшен в случае ошибки.
     */
    public static List<File> split(File file, File dstDir, int size) throws IOException {
        int partCounter = 1;
        List list = new ArrayList(size);
        byte[] fileSize = new byte[size];
        int readBytes;
        try (FileInputStream fileInputStream = new FileInputStream(file);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)) {
            while ((readBytes = bufferedInputStream.read(fileSize)) > 0) {
                String partlyName = String.format("%s.%03d", pathName + "\\out" + "\\" + dstDir, partCounter++);
                File newFile = new File(dstDir.getParent(), partlyName);
                try (FileOutputStream out = new FileOutputStream(newFile)) {
                    out.write(fileSize, 0, readBytes);
                    list.add(newFile);
                }
            }
        }
        return list;
    }

    /**
     * Собирает из частей один файл.
     *
     * @param files Список файлов в порядке чтения.
     * @param dst   Файл, в который будут записаны все части.
     * @throws IOException Будет выброшен в случае ошибки.
     */
    public static void assembly(List<File> files, File dst) throws IOException {
        int readBytes = 0;
        File fullFile = new File(System.getProperty("user.dir") + "\\out" + "\\" + dst);
        try (FileOutputStream fileOutputStream = new FileOutputStream(fullFile);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {
            for (File file : files) {
                //Files.copy(file.toPath(), bufferedOutputStream); //abstract path
                FileInputStream fileInputStream = new FileInputStream(file);
                while ((readBytes = fileInputStream.read(buffer)) >= 0)
                    fileOutputStream.write(buffer, 0, readBytes);
            }
        }
    }

    /**
     * Шифрует/дешифрует поток с помощью XOR. В качестве ключа используется пароль.
     *
     * @param src        Входящий поток, данные которого будут зашифрованы/расшифрованы.
     * @param dst        Выходящий поток, куда будут записаны зашифрованные/расшифрованные данные.
     * @param passphrase Пароль.
     * @throws IOException Будет выброшен в случае ошибки.
     */
    public static void encrypt(InputStream src, OutputStream dst, String passphrase) throws IOException {
        int len;
        byte[] buf = new byte[1024];
        while ((len = src.read(buf)) > 0) {
            encrypt(buf, passphrase);
            dst.write(buf, 0, len);
        }
    }

    /**
     * Шифрует/дешифрует файл с помощью файла-ключа.
     *
     * @param src Файл, который должен быть зашифрован/расшифрован.
     * @param dst Файл, куда будут записаны зашифрованные/расшифрованные данные.
     * @param key Файл-ключ.
     * @throws IOException Будет выброшен в случае ошибки.
     */
    public static void encrypt(File src, File dst, File key) throws IOException {
        byte[] bufIn = new byte[1024];
        String passphrase = getString(key);
        try (InputStream in = new BufferedInputStream(new FileInputStream(src));
             OutputStream out = new BufferedOutputStream(new FileOutputStream(dst))) {
            encrypt(in, out, passphrase);
        }
    }

    /**
     * Шифрует/дешифрует массив байт с помощью ключевой строки
     *
     * @param array      массив, который должен быть зашифрован/расшифрован.
     * @param passphrase строка, с помощью которой должен быть зашшифрован/расшифрован массив.
     */
    private static void encrypt(byte[] array, String passphrase) {
        encrypt(array, passphrase.getBytes());
    }

    /**
     * Шифрует/дешифрует массив байт с помощью ключивого массива байт
     *
     * @param array массив, который должен быть зашифрован/расшифрован.
     * @param key   массив, с помощью которого должен быть зашшифрован/расшифрован массив.
     */
    private static void encrypt(byte[] array, byte[] key) {
        for (int i = 0, j = 0; i < array.length; i++) {
            array[i] ^= key[j];
            j = ++j < key.length ? j : 0;
        }
    }

    /**
     * Возвращает строку полученную из файла
     *
     * @param file файл, из которого должна быть получена строка.
     * @return {@code String} строка.
     * @throws IOException
     */
    private static String getString(File file) throws IOException {
        StringBuilder sb = new StringBuilder();
        char[] chars = new char[1024];
        try (Reader reader = new FileReader(file)) {
            int len;
            while ((len = reader.read(chars)) > 0) {
                sb.append(chars, 0, len);
            }
        }
        return sb.toString();
    }
}