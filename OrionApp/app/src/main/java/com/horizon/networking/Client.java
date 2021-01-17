package com.horizon.networking;

import android.annotation.SuppressLint;
import android.util.Log;
import android.util.Xml;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * A client to connect the server.
 */
public class Client {
    private final Socket clientSocket;
    private final DataInputStream input;
    private final InputStreamReader cryptoInput;
    private final PrintWriter output;
    private final OutputStreamWriter cryptoOutput;

    private String name = "Comp";

    private final String ip = "192.168.1.34";
    private final int port = 1690;

    private Encryption encryption = null;

    public Client() throws IOException {
        this.clientSocket = new Socket(ip, port);

        this.output = new PrintWriter(this.clientSocket.getOutputStream());
        this.cryptoOutput = new OutputStreamWriter(this.clientSocket.getOutputStream(),
                StandardCharsets.UTF_16);

        this.input = new DataInputStream(this.clientSocket.getInputStream());
        this.cryptoInput = new InputStreamReader(clientSocket.getInputStream(),
                StandardCharsets.UTF_16);
    }

    public void setEncryption(Encryption encryption) {
        this.encryption = encryption;
    }

    /**
     * Sends a msg through the network
     * @param msg the msg to send
     * @throws IOException -
     */
    @SuppressLint("DefaultLocale")
    public void send(String msg) throws IOException {
        String length;
        if(this.encryption == null) {
            length = String.format("%03d", msg.length());
            this.output.write(length);
            this.output.flush();
            System.out.println("send: " + length);

            this.output.write(msg);
            System.out.println("send: " + msg);

        } else {
            length = String.format("%03d", msg.length() * 4);
            this.output.write(length);
            this.output.flush();

            System.out.println("send: " + msg);
            msg = this.encryption.encryptMsg(msg);
            this.cryptoOutput.write(msg);
            this.cryptoOutput.flush();
        }

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
        System.out.println(1);
        int length = 0;
        if(this.encryption == null) {
            for (int i = 0, dev = 100; i < NetworkPackets.HEADER; i++, dev /= 10) {
                length += Character.getNumericValue(input.read()) * dev;
            }

            StringBuilder msg = new StringBuilder();
            for (int i = 0; i < length; i++) {
                msg.append((char) input.read());
            }

            System.out.println("recv " + length +" " + msg);
            return msg.toString();
        } else {
            System.out.println(2);

            for (int i = 0, dev = 100; i < NetworkPackets.HEADER; i++, dev /= 10) {
                length += Character.getNumericValue(input.read()) * dev;
            }
            System.out.println(length);

            StringBuilder msg = new StringBuilder();
            for (int i = 0; i < length / 4; i++) {
                msg.append((char) cryptoInput.read());
                System.out.println("r " + msg.toString());
            }
            String finalMsg = encryption.decryptMsg(msg.toString());
            System.out.println("recv " + length +" " + finalMsg);
            return finalMsg;
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}