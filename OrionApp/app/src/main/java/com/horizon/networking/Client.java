package com.horizon.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    private Socket clientSocket;
    private InetSocketAddress address;
    private InputStreamReader input;
    private PrintWriter output;
    private BufferedReader reader;
    private String name = "Comp";

    public Client(String ip, int port) throws IOException {
        this.address = new InetSocketAddress(ip, port);
        this.clientSocket = new Socket(ip, port);
        this.output = new PrintWriter(this.clientSocket.getOutputStream());
        this.input = new InputStreamReader(this.clientSocket.getInputStream());
        this.reader = new BufferedReader(this.input);
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
        int length = 0;
        for (int i = 0, dev = 100; i < NetworkPackets.HEADER; i++, dev /= 10) {
            length += Character.getNumericValue(input.read()) * dev;
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