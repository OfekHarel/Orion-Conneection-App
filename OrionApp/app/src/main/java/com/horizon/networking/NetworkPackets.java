package com.horizon.networking;

/**
 * A util class to help the flow of the communication between the devices.
 */
public final class NetworkPackets {
    public final static String SEPERATOR = "!";
    public final static int HEADER = 3;

    /**
     * Creates a msg that follows the protocol rules.
     * 
     * @param msg the msg parts to send
     * @return the protocol based built msg
     */
    public static String assamble(String... msg) {
        String finalMsg = "";
        for (String string : msg) {
            finalMsg += String.format("%s%s", string, SEPERATOR);
        }
        return finalMsg;
    }

    /**
     * splits the msg to a list by the net protocol.
     * 
     * @param msg the raw msg from the net
     * @return String array that each index contains the value of the msg's parts
     */
    public static String[] split(String msg) {
        return msg.split(SEPERATOR);
    }

    /**
     * An enum that contains the incoming network state msgs.
     */
    public enum IncomingOperations {
        INVALID("INVALID"), VALID("VALID"), PAIRED("HELLO"), CONNECT("CONN");

        private final String msg;

        private IncomingOperations(String msg) {
            this.msg = msg;
        }

        public String getAsString() {
            return msg;
        }
    }
}