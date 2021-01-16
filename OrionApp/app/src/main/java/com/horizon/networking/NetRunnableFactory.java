package com.horizon.networking;

import android.os.Handler;
import android.util.Log;
import android.util.Pair;

import com.horizon.utils.Vars;
import com.horizon.utils.conn.SingleConnection;

import java.util.ArrayList;

public class NetRunnableFactory {

    public static NetCommRunnable get(String name) {
        ArrayList<Pair<String, NetCommRunnable>> arrayList = Vars.names;

        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).first.equals(name)) {
                return arrayList.get(i).second;
            }
        }
        NetCommRunnable inst = new NetCommRunnable();
        Pair<String, NetCommRunnable> p = new Pair<>(name, inst);
        Vars.names.add(p);
        Vars.actions ++;
        return p.second;
    }

    public static void passAnAction(String name, Executioner.Actions act) {
        for (int i = 0; i < Vars.names.size(); i++) {
            if (Vars.names.get(i).first.equals(name)) {
                Vars.names.get(i).second.setAction(act);
            }
        }
    }

    public static ArrayList<SingleConnection> buildFromBlueprints(ArrayList<SingleConnection> arr) {
        if (arr == null) {
            return null;
        }

        for (int i = 0; i < arr.size(); i++) {
            arr.get(i).setRunnable(NetRunnableFactory.get(arr.get(i).getName()));
            arr.get(i).initConnection();
        }
        return arr;
    }
}
