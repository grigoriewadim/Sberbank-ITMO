package com.ifmo.lesson24.udp;

import com.ifmo.lesson20.chat.IOUtils;

import java.io.*;
import java.net.*;

import static java.lang.Thread.*;

public class UDPClient {

    private void createAndListenSocket() {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("localhost");
            byte[] read = new byte[1024];
            File outFile = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\wap.txt");
            FileInputStream fileInputStream = new FileInputStream(outFile);
            byte[] outArray = fileInputStream.readAllBytes();
            //System.out.println(outArray.length);
            while (fileInputStream.read() != -1) {
                DatagramPacket sendPacket = new DatagramPacket(outArray, outArray.length, IPAddress, 4401);
                socket.send(sendPacket);
            }
            System.out.println("Отправлено");
            DatagramPacket incomingPacket = new DatagramPacket(read, read.length);
            socket.receive(incomingPacket);
            String response = new String(incomingPacket.getData());
            System.out.println("Ответ сервера: " + response);
            sleep(2000);

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UDPClient client = new UDPClient();
        client.createAndListenSocket();
    }
}