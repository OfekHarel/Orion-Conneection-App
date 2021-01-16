package com.horizon.networking;

import android.os.Handler;
import android.util.Log;
import android.util.Pair;

import com.horizon.utils.Vars;
import com.horizon.utils.conn.SingleConnection;

import java.util.ArrayList;

/**
 * A factory class to create and manage the instances of each runnable.
 * It's responsible of connection between each Single 
 * Connection and for it's runnable.<p>
 * This class came from the need to maitain a somekind of association between
 * a Connection to it's matching runnable for future reffernces.
 * Duo to the Shared Preffernce the instances are not in a static mem address.
 */
public class NetRunnableFactory {

    /** 
     * Returns or creates an instance of a NetCommRunnable according to the name.
     * @param name - the connection's name
     * @return the runnable instance that's associated with the name.
     */
    public static NetCommRunnable get(String name) {
        ArrayList<Pair<String, NetCommRunnable>> arrayList = Vars.names;

        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).first.equals(name)) {
                return arrayList.get(i).second;
            }
        }
        // Creates a new one if needed.
        NetCommRunnable inst = new NetCommRunnable();
        Pair<String, NetCommRunnable> p = new Pair<>(name, inst);
        Vars.names.add(p);
        return p.second;
    }

    /**
     * This function's resposible of passing an action to the runnable.
     * Call this function when need to create a communiction flow to the 
     * connection's computer.
     * @param name - The name of the connection
     * @param act - The Action to pass
     */
    public static void passAnAction(String name, Executioner.Actions act) {
        for (int i = 0; i < Vars.names.size(); i++) {
            if (Vars.names.get(i).first.equals(name)) {
                Vars.names.get(i).second.setAction(act);
            }
        }
    }

    /**
     * This Function builds the factory from scartch and it's respnible of 
     * remaping any association between the connection to it's runnable.
     * This function allows to maintain the paired connections.
     * @param arr - An array list of Single connections right from the Shared Preffrences
     * @return - The edited and updated arr.
     */
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
