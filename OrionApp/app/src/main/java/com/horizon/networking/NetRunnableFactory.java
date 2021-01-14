package com.horizon.networking;

import android.util.Pair;

import com.horizon.utils.Vars;

import java.util.ArrayList;

public class NetRunnableFactory {

    public static NetCommRunnable get(String name) {
        ArrayList<Pair<String, NetCommRunnable>> arrayList = Vars.names;

        for (int i = 0; i < arrayList.size() && !arrayList.isEmpty(); i++) {
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
}
