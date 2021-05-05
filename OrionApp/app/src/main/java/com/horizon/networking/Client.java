package com.horizon.networking;

import android.annotation.SuppressLint;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * A client to connect the server.
 */
public class Client {
    private final Socket clientSocket;
    private final DataInputStream input;
    private final InputStreamReader cryptoInput;
    private final OutputStreamWriter output;
    private final OutputStreamWriter cryptoOutput;

    private String name = "Comp";

    private final String PUBLIC_IP = "192.46.233.145";
    private final String LOCAL_IP = "192.168.1.41";
    private final int PORT = 1691;

    private Encryption encryption = null;

    public Client() throws Exception {
        this.clientSocket = new Socket();
        this.clientSocket.connect(new InetSocketAddress(this.LOCAL_IP, this.PORT), 1000);

        this.output = new OutputStreamWriter(this.clientSocket.getOutputStream(), StandardCharsets.UTF_8);
        this.cryptoOutput = new OutputStreamWriter(this.clientSocket.getOutputStream(), StandardCharsets.UTF_16LE);

        this.input = new DataInputStream(this.clientSocket.getInputStream());
        this.cryptoInput = new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_16LE);
    }

    public void setEncryption(Encryption encryption) {
        this.encryption = encryption;
    }

    /**
     * Sends a msg through the network
     * 
     * @param msg the msg to send
     * @throws IOException -
     */
    @SuppressLint("DefaultLocale")
    public void send(String msg) throws IOException {
        String length;
        if (this.encryption == null) {
            length = String.format("%04d", msg.length());
            this.output.write(length);
            this.output.flush();
            this.output.write(msg);

        } else {
            length = String.format("%04d", (msg.length() * 4));
            this.output.write(length);
            this.output.flush();

            msg = this.encryption.encryptMsg(msg);

            this.cryptoOutput.write(msg);
            this.cryptoOutput.flush();
        }
    }

    /**
     * Receives a msg through the network.
     * 
     * @return the received net msg.
     * @throws NumberFormatException -
     * @throws IOException           -
     */
    public String receive() throws NumberFormatException, IOException {
        if (input.available() < 2) {
            return "";
        }

        int length = 0;
        if (this.encryption == null) {
            for (int i = 0, dev = 1000; i < NetworkPackets.HEADER; i++, dev /= 10) {
                length += Character.getNumericValue(input.read()) * dev;
            }

            StringBuilder msg = new StringBuilder();
            for (int i = 0; i < length; i++) {
                msg.append((char) input.read());
            }

            return msg.toString();
        } else {

            for (int i = 0, dev = 1000; i < NetworkPackets.HEADER; i++, dev /= 10) {
                length += Character.getNumericValue(input.read()) * dev;
            }

            StringBuilder msg = new StringBuilder();
            for (int i = 0; i < length / 4; i++) {
                msg.append((char) cryptoInput.read());
            }
            String finalMsg = encryption.decryptMsg(msg.toString());
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