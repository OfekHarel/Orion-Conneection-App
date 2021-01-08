package com.horizon.networking;

import java.io.IOException;

public class Executioner {
    private Client client;

    public Executioner(Client client) {
        this.client = client;
    }

    public void Execute(Actions action) throws IOException {
        this.client.send(NetworkPackets.assamble(this.client.getName(), action.getAsString()));
        System.out.println(NetworkPackets.assamble(this.client.getName(), action.getAsString()));
    }

    public boolean sync(String id, String compName) throws IOException {
        this.client.setName(compName);
        this.client.send(NetworkPackets.assamble("APP", Actions.ID_VALIDATION.getAsString(), id));
        
        String val = "";
        while (val == "") {
            val = this.client.recieve();
        }
        System.out.println("This is america " + val);
        boolean is = NetworkPackets.split(val)[0].equals(NetworkPackets.IncomingOperations.VALID.getAsString());
        if (!is) {
            System.out.println("one");
            return false;
        }
        this.client.send(NetworkPackets.assamble(compName));
        System.out.println("one3339");
        val = this.client.recieve();
        return true;
    }

    public enum Actions {
        VOL_UP("VOL_UP"),
        VOL_DOWN("VOL_DOWN"),
        PAUSE_PLAY_TOGGLE("PTT"),
        SKIP("SKIP"),
        PREV("PREV"),
        MUTE("MUTE"),
        OFF("OFF"),
        SLEEP("SLEEP"),
        RESTART("RESTRT"),
        LOCK("LCK"),
        LOG_OUT("LGOT"),
        DISCONNECT("DISCON"),
        ID_VALIDATION("ID_VAL");

        private final String str;
        private Actions(String str) {
            this.str = str;
        }

        public String getAsString() {
            return str;
        }
    }

}

