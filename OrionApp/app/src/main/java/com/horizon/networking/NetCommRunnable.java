package com.horizon.networking;

import android.util.Log;
import android.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import com.horizon.networking.Executioner.Actions;
import com.horizon.utils.SharedData;
import com.horizon.utils.Vars;
import com.horizon.utils.conn.SingleConnection;

public class NetCommRunnable implements Runnable {
    private Client client;
    private Executioner executioner;
    private String recvMsg = "";

    private Actions act;
    private String id = "";
    private String name = "";

    private boolean synced = false;


    public void pair(String idInfo, String nameInfo) {
        id = idInfo;
        name = nameInfo;
    }

    public Client getClient() {
        return client;
    }

    public void setAction(Actions action) {
        act = action;
    }

    private void act() throws IOException {
        if(act != null) {
            executioner.Execute(act);
            act = null;
        }
    }

    public NetCommRunnable() {
        act = null;
    }


    @Override
    public void run() {
        /*
         * Trying to connect
         */
        try {
            client = new Client();
            executioner = new Executioner(client);
            synced = executioner.sync(id, name);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
                Log.i("shitshitshitshitshitshitshitshitshitshitshitshitshitshit", "1");
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
