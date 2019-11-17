package com.ifmo.lesson15;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/*
    Реализуйте все методы с использованием потоков ввода-вывода.
 */
public class IOStreamTasks {
    private static byte[] buffer = new byte[2048];

    public static void main(String[] args) {
        File src = new File("D:\\!JAVA!\\Projects\\Sberbank-ITMO-master\\src\\main\\resources\\wap.txt");
        File dst = new File("D:\\!JAVA!\\Projects\\Sberbank-ITMO-master\\src\\main\\resources\\wap_copy.txt");
        try (InputStream in = new FileInputStream(src);
             OutputStream out = new FileOutputStream(dst)) {
            copy(in, out);
            assembly(split(dst, new File("wap_part.txt"), 102400), new File("fullFile.txt"));
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
        //byte[] buffer = new byte[1024];
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
                String partlyName = String.format("%s.%03d", System.getProperty("user.dir") + "\\out" + "\\" + dstDir, partCounter++);
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
        //byte[] buffer = new byte[1024];
        int readBytes = 0;
        File fullFile = new File(System.getProperty("user.dir") + "\\out" + "\\" + dst);
        try (FileOutputStream fileOutputStream = new FileOutputStream(fullFile);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {
            for (File file : files) {
                Files.copy(file.toPath(), bufferedOutputStream); //abstract path
                FileInputStream fileInputStream = new FileInputStream(file);
                while ( (readBytes = fileInputStream.read(buffer)) >= 0)
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

    }
}