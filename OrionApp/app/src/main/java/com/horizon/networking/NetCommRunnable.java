package com.horizon.networking;

import android.util.Log;

import java.io.IOException;

import com.horizon.networking.Executioner.Actions;

/**
 * A runnable class that's responsible of 
 * the passing flow of the communication.
 */
public class NetCommRunnable implements Runnable {
    private Client client;
    private Executioner executioner;
    private String recvMsg = "";

    private Actions act;
    private String id = "";
    private String name = "";

    private boolean synced = false;
    public boolean isDoneSynceProc = false;

    public NetCommRunnable() {
        act = null;
    }

    /**
     * A function that responsible to pass the sync required
     * @param idInfo - The ID
     * @param nameInfo - The Connection's Name
     * @return -
     */
    public boolean pair(String idInfo, String nameInfo) {
        id = idInfo;
        name = nameInfo;

        synced = false;
        isDoneSynceProc = false;
        /*
         * Trying to connect
         */
        try {
            client = new Client();
            executioner = new Executioner(client);
            synced = executioner.sync(id, name);
        } catch (IOException e) {
            synced = false;
            e.printStackTrace();
        }
        Log.i("ooooooooooooooooooooooooooooooooo", Boolean.toString(synced));
        isDoneSynceProc = true;
        return synced;
    }

    public Client getClient() {
        return client;
    }

    public void setAction(Actions action) {
        act = action;
    }

    /**
     * Passes the action to the exeutioner.
     * @throws IOException -
     */
    private void act() throws IOException {
        if(act != null) {
            executioner.Execute(act);
            act = null;
        }
    }

    /**
     * @return if the connection is synced
     */
    public boolean isSynced() {
        return synced;
    }

    @Override
    public void run() {

        /*
         * Connected
         */
        while (synced) {
            /*
             * send
             */
            try {
                act();
            } catch (IOException  e) {
                e.printStackTrace();
            }

            /*
             * recv
             */
            try {
                recvMsg = client.receive();
            } catch (NumberFormatException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
