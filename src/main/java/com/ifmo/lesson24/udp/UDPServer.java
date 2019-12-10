package com.ifmo.lesson24.udp;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {

    private void createAndListenSocket() {
        try {
            DatagramSocket socket = new DatagramSocket(9876);
            byte[] incomingData = new byte[1024];
            while (true) {
                DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
                socket.receive(incomingPacket);
                byte[] data = incomingPacket.getData();
                ByteArrayInputStream in = new ByteArrayInputStream(data);
                FileOutputStream output = new FileOutputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\receive.txt");
                int bufferSize = 1024;
                byte[] buffer = new byte[bufferSize];
                int n;
                n = in.read(buffer, 0, bufferSize);
                while (n >= 0) {
                    output.write(buffer, 0, n);
                    n = in.read(buffer, 0, bufferSize);
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