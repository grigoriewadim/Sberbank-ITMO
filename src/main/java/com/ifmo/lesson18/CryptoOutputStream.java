package com.ifmo.lesson18;

import java.io.*;

/**
 * Реализовать шифрующий (XOR) поток вывода.
 */
public class CryptoOutputStream extends FilterOutputStream {
    /**
     * Создаёт новый {@link CryptoOutputStream}.
     * При записи применяет операцию XOR последовательно:
     * каждый байт из ключа ^ каждый байт из выходящего потока.
     * Когда встречается конец ключа, то на следующий байт потока
     * берётся первый байт из ключа (по принципу кольцевого буфера).
     *
     * @param out Поток вывода.
     * @param key Ключ шифрования.
     */
    public CryptoOutputStream(OutputStream out, byte[] key) throws IOException {
        super(out);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        for (int i = 0; i < key.length; i++) {
            bos.write(bos.toByteArray());
        }



        bos.close();
        out.close();
    }
}
