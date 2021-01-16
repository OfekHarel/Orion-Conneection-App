package com.horizon.networking;

import android.annotation.SuppressLint;
import android.util.Log;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * A client to connect the server.
 */
public class Client {
    private Socket clientSocket;
    private InetSocketAddress address;
    private DataInputStream input;
    private PrintWriter output;
    private String name = "Comp";

    private final String ip = "192.168.1.34";
    private final int port = 1690;

    public Client() throws IOException {
        this.address = new InetSocketAddress(ip, port);
        this.clientSocket = new Socket(ip, port);
        this.output = new PrintWriter(this.clientSocket.getOutputStream());
        this.input = new DataInputStream(this.clientSocket.getInputStream());
    }

    /**
     * Sends a msg through the network
     * @param msg the msg to send
     * @throws IOException -
     */
    public void send(String msg) throws IOException {
        @SuppressLint("DefaultLocale")
        String length = String.format("%03d",msg.length());
        this.output.write(length);
        this.output.flush();
        System.out.println("send: " + length);

        this.output.write(msg);
        this.output.flush();
        System.out.println("se vcx cnd: " + msg);
    }

    /**
     * Receives a msf through the network.
     * @return the received net msg.
     * @throws NumberFormatException -
     * @throws IOException -
     */
    public String receive() throws NumberFormatException, IOException {
        if (input.available() < 2) {
            return "";
        }

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