package com.horizon.networking;

import java.io.IOException;

/**
 * A class that responsible to handle one client to execute it's income and outcomes.
 */
public class Executioner {
    private Client client;

    public Executioner(Client client) {
        this.client = client;
    }

    /**
     * The main function to run periodically that handles the wanted action.
     * @param action the wanted action to execute.
     * @throws IOException -
     */
    public void Execute(Actions action) throws IOException {
        this.client.send(NetworkPackets.assamble(this.client.getName(), action.getAsString()));
    }

    /**
     * A function that handles the sync state of the connections.
     * @param id - the device id
     * @param compName - the connections name
     * @return whether it synced
     * @throws IOException -
     */
    public boolean sync(String id, String compName) throws IOException {
        this.client.setName(compName);
        this.client.send(NetworkPackets.assamble("APP", Actions.ID_VALIDATION.getAsString(), id));
        
        String val = "";
        while (val == "") {
            val = this.client.receive();
        }
        boolean is = NetworkPackets.split(val)[0].equals(NetworkPackets.IncomingOperations.VALID.getAsString());
        if (!is) {
            return false;
        }
        this.client.send(NetworkPackets.assamble(compName));
        val = this.client.receive();
        return true;
    }

    /**
     * An enum that represent the actions that can be done through the app.
     */
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

