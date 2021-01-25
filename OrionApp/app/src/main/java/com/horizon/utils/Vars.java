package com.horizon.utils;

import android.util.Pair;

import com.horizon.networking.Executioner.Actions;
import com.horizon.networking.NetCommRunnable;
import com.horizon.utils.conn.GroupConnection;
import com.horizon.utils.conn.SingleConnection;

import java.util.ArrayList;

/**
 * A class that contains a reusable temp static vars.
 */
public class Vars {
    /**
     * A boolean var to provide info about the pair / control process. whether it's
     * a group or not.
     */
    public static boolean isFromGroup = false;

    /**
     * A GroupConnection var to help and provide a temp var of a group connection.
     * Used in the pair / control group process.
     */
    public static GroupConnection newGroup = new GroupConnection("", new ArrayList<SingleConnection>());

    /**
     * A Single Connection var to help and provide a temp var of a group connection.
     * Used in the pair / control group process.
     */
    public static SingleConnection connection = null;

    /**
     * An array list that contains the connection between each connection to a
     * runnable.
     */
    public static ArrayList<Pair<String, NetCommRunnable>> names = new ArrayList<>();

    public static String toastText = "";

    public static boolean DEBUG = true;
}
