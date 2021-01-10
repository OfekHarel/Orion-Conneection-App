package com.horizon.networking;

import android.util.Log;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    private Socket clientSocket;
    private InetSocketAddress address;
    private DataInputStream input;
    private PrintWriter output;
    private String name = "Comp";

    private final String ip = "192.168.1.10";
    private final int port = 1690;

    public Client() throws IOException {
        this.address = new InetSocketAddress(ip, port);
        this.clientSocket = new Socket(ip, port);
        this.output = new PrintWriter(this.clientSocket.getOutputStream());
        this.input = new DataInputStream(this.clientSocket.getInputStream());
    }

    public void send(String msg) throws IOException {
        String length = String.format("%03d",msg.length());
        this.output.write(length);
        this.output.flush();
        System.out.println("send: " + length);

        this.output.write(msg);
        this.output.flush();
        System.out.println("send: " + msg);
    }

    public String recieve() throws NumberFormatException, IOException {
        if (input.available() < 2) {
            return "";
        }
        int length = 0;
        for (int i = 0, dev = 100; i < NetworkPackets.HEADER; i++, dev /= 10) {
            length += Character.getNumericValue(input.read()) * dev;
            System.out.println(length);
        }
        String msg = "";
        for (int i = 0; i < length; i++) {
            msg += Character.toString((char) input.read());
        }
        System.out.println("recv " + length +" " + msg);
        return msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}