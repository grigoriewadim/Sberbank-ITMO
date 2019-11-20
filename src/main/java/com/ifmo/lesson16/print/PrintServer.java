package com.ifmo.lesson16.print;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PrintServer implements Externalizable {

    private int port;
    private SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");

    public PrintServer(int port) {
        this.port = port;
    }

    private void start() throws IOException {
        try (ServerSocket ssocket = new ServerSocket(port)) {
            System.out.println("Server started on " + ssocket);

            while (true) {
                Socket sock = ssocket.accept();
                try {
                    process(sock);
                } catch (ClassNotFoundException e) {
                    System.err.println("Wrong message was received");
                    e.printStackTrace();
                } finally {
                    sock.close();
                }
            }
        }
    }

    private void process(Socket sock) throws IOException, ClassNotFoundException {
        String host = sock.getInetAddress().getHostAddress();
        try (ObjectInputStream objIn = new ObjectInputStream(sock.getInputStream());
            OutputStream out = sock.getOutputStream()) {
            Object obj = objIn.readObject();
            try {
                ObjectOutputStream outputStream = new ObjectOutputStream(out);
                if (obj.toString().contains("/ping")) {
                    outputStream.write(Integer.parseInt("Ping......"));
                }
                if (obj.toString().contains("/server_time")) {
                    Date data = new Date();
                    outputStream.write(Integer.parseInt(String.valueOf(data.getTime())));
                }
                if (obj.toString().contains("/list_users")) {
                    outputStream.write(Integer.parseInt("User_list"));
                }
                System.out.println(out.toString());
                out.flush();
            }catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException | ClassNotFoundException | RuntimeException e) {
            System.err.println("Failed process connection from: " + host);
            e.printStackTrace();
            throw e;
        }
    }

    private void printMessage(Message msg, String senderAddr) {
        System.out.printf("%s (%s) at %s wrote: %s\n", msg.getSender(), senderAddr, format.format(new Date(msg.getTimestamp())), msg.getText());
    }

    public static void main(String[] args) throws IOException {
        if (args == null || args.length == 0)
            throw new IllegalArgumentException("Port must be specified");
        int port = Integer.parseInt(args[0]);
        PrintServer printServer = new PrintServer(port);
        printServer.start();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
//        out.writeInt();
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
//        in.readInt();
    }
}