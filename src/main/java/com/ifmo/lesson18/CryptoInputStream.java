package com.ifmo.lesson18;

import java.io.*;

/**
 * Реализовать шифрующий (XOR) поток вывода.
 */
public class CryptoInputStream extends FilterInputStream {
    /**
     * Создаёт новый {@link CryptoInputStream}.
     * При чтении применяет операцию XOR последовательно:
     * каждый байт из ключа ^ каждый байт из входящего потока.
     * Когда встречается конец ключа, то на следующий байт потока
     * берётся первый байт из ключа (по принципу кольцевого буфера).
     *
     * @param in Поток ввода.
     * @param key Ключ шифрования.
     */
    public CryptoInputStream(InputStream in, byte[] key) throws IOException {
        super(in);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte [] byteArray = new byte[1024];
        int readByte;
        while ((readByte = in.read()) != -1) {
            for (int i = 0; true; i++) {
                bos.write(readByte ^ key[i]);
            }
        }
        bos.close();
        in.close();
    }

}
