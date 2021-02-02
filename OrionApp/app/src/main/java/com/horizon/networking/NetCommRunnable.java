package com.horizon.networking;

import java.io.IOException;
import com.horizon.networking.Executioner.Actions;

/**
 * A runnable class that's responsible of the passing flow of the communication.
 */
public class NetCommRunnable implements Runnable {
    private Client client;
    private Executioner executioner;

    public String recvMsg;
    private Actions act;
    private String id = "";
    private String name = "";

    private boolean synced = false;
    public boolean isDoneSyncProc = false;

    public NetCommRunnable() {
        act = null;
        recvMsg = "";
    }

    /**
     * A function that responsible to pass the sync required
     * 
     * @param idInfo   - The ID
     * @param nameInfo - The Connection's Name
     * @return -
     */
    public boolean pair(String idInfo, String nameInfo) {
        id = idInfo;
        name = nameInfo;

        synced = false;
        isDoneSyncProc = false;
        /*
         * Trying to connect
         */
        try {
            client = new Client();
            executioner = new Executioner(client);
            synced = executioner.sync(id, name);

        } catch (Exception e) {
            synced = false;
            e.printStackTrace();
        }
        isDoneSyncProc = true;
        return synced;
    }

    public Client getClient() {
        return client;
    }

    public void setAction(Actions action) {
        act = action;
    }

    public String getRecvMsg() {
        return recvMsg;
    }

    public void setRecvMsg(String recvMsg) {
        this.recvMsg = recvMsg;
    }

    /**
     * Passes the action to the executioner.
     * @throws IOException -
     */
    private void act() throws IOException {
        if (act != null) {
            executioner.Execute(act);
            act = null;
        }
    }

    /**
     * recv a network packet
     * @throws IOException -
     */
    private void recvPacket() throws IOException {
        String msg = client.receive();
        if (!msg.equals("")) {
            setRecvMsg(msg);
        }
    }

    /**
     * The periodic function
     */
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
            } catch (IOException e) {
                e.printStackTrace();
            }

            /*
             * recv
             */
            try {
                recvPacket();
            } catch (NumberFormatException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
