package com.ifmo.lesson24.udp;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {

    private void createAndListenSocket() {
        try {
            DatagramSocket socket = new DatagramSocket(4401);
            System.out.println(socket.getPort());
            System.out.println(socket.isBound());
            byte[] incomingData = new byte[1024];
            while (true) {
                if (socket.isConnected()) {
                    System.out.println("Соединение установлено! " + socket.getPort());
                }
                DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
                socket.receive(incomingPacket);
                byte[] data = incomingPacket.getData();
                ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
                FileOutputStream outputFile = new FileOutputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\receive.txt");
                byte[] buffer = new byte[1024];
                int len = inputStream.read(buffer, 0, buffer.length);
                System.out.println(len);
                while (len > 0) {
                    outputFile.write(buffer, 0, len);
                    len = inputStream.read(buffer, 0, buffer.length);
                }
                InetAddress IPAddress = incomingPacket.getAddress();
                int port = incomingPacket.getPort();
                String reply = "Сообщение получено";
                byte[] replyBytea = reply.getBytes();
                DatagramPacket replyPacket =
                        new DatagramPacket(replyBytea, replyBytea.length, IPAddress, port);
                socket.send(replyPacket);
                Thread.sleep(2000);
                System.exit(0);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UDPServer server = new UDPServer();
        server.createAndListenSocket();
    }
}