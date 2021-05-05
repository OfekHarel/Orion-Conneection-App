package com.horizon.networking;

import java.math.BigInteger;

/**
 * This class is responsible of containing the necessary keys to decrypt and encrypt incoming and
 * outgoing msgs.
 * Base on Deffie Helman algorithm
 */
public class Encryption {

    private final BigInteger n, g, privateKey;
    private BigInteger fullKey;

    /**
     * Builds a new Encryption type with it's own unique keys.
     * @param g - the G num.
     * @param n - the N num.
     * @param privateKey - the private key.
     */
    public Encryption(BigInteger g, BigInteger n, BigInteger privateKey) {
        this.n = n;
        this.g = g;
        this.privateKey = privateKey;
    }

    /**
     * @return the N num.
     */
    public BigInteger getN() {
        return n;
    }

    /**
     * @return the G num.
     */
    public BigInteger getG() {
        return g;
    }

    /**
     * @return the full key.
     */
    public BigInteger getFullKey() {
        return fullKey;
    }

    /**
     * @return the partial key for the key transfer process
     */
    public BigInteger getPartialKey() {
        return g.modPow(privateKey, n);
    }

    /**
     * This function generate the full ket from the other partial key.
     * @param otherPartial - the (g^b)mod(n) rom the net.
     * @return the full key.
     */
    public BigInteger getFullKey(BigInteger otherPartial) {
        BigInteger num = otherPartial.modPow(privateKey, n);
        this.fullKey = num;
        return num;
    }

    /**
     * @param msg - the msg to encrypt
     * @return the encrypted msg
     */
    public String encryptMsg(String msg) {
        String encMsg = "";
        int ord;
        char[] arr = msg.toCharArray();
        for (char c : arr) {
            ord = ((int) (c)) + this.fullKey.intValue();
            ord %= 256;
            encMsg += (char) Math.abs(ord);
        }
        return encMsg;
    }

    /**
     * @param msg - the msg to decrypt
     * @return the decrypt msg
     */
    public String decryptMsg(String msg) {
        String decMsg = "";
        int ord;
        char[] arr = msg.toCharArray();
        for (char c : arr) {
            ord = ((int) (c)) - this.fullKey.intValue();
            ord %= 256;
            decMsg += (char) ord;
        }
        return decMsg;
    }
}
