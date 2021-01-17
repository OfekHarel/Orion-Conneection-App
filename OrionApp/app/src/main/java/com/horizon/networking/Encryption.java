package com.horizon.networking;

import java.math.BigInteger;

public class Encryption {

    private final BigInteger n, g, privateKey;
    private BigInteger fullKey;
    public Encryption(BigInteger g, BigInteger n, BigInteger privateKey){
        this.n = n;
        this.g = g;
        this.privateKey = privateKey;
    }

    public BigInteger getN() {
        return n;
    }

    public BigInteger getG() {
        return g;
    }

    public BigInteger getPrivateKey() {
        return privateKey;
    }

    public BigInteger getFullKey() {
        return fullKey;
    }

    public BigInteger getPartialKey() {
        return g.modPow(privateKey, n);
    }

    public BigInteger getFullKey(BigInteger otherPartial) {
        BigInteger num = otherPartial.modPow(privateKey, n);
        this.fullKey = num;
        return num;
    }

    public String encryptMsg(String msg) {
        String encMsg = "";
        char[] arr = msg.toCharArray();
        for (char c : arr) {
            encMsg += (char) ((int)(c) + this.fullKey.intValue());
            System.out.println((int)(c) + this.fullKey.intValue());
        }
        return encMsg;
    }

    public String decryptMsg(String msg) {
        String decMsg = "";
        char[] arr = msg.toCharArray();
        for (char c : arr) {
            decMsg += (char) ((int)(c) - this.fullKey.intValue());
        }
        return decMsg;
    }
}
