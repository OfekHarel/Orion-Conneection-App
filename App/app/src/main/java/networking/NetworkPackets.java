public final class NetworkPackets {
    public final static String SEPERATOR = "!";
    public final static int HEADER = 3;

    public static String assamble(String... msg) {
        String finalMsg = "";
        for (String string : msg) {
            finalMsg += String.format("%s%s", string, SEPERATOR);
        }
        return finalMsg;
    }

    public static String[] split(String msg) {
        return msg.split(SEPERATOR);
    }

    public enum IncomingOperations {
        INVALID("INVALID"),
        VALID("VALID");

        private final String msg;
        private IncomingOperations(String msg) {
            this.msg = msg;
        }

        public String getAsString() {
            return msg;
        }
    }
}