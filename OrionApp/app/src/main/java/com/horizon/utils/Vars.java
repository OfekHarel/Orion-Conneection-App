package com.horizon.utils;

import android.util.Pair;

import com.horizon.networking.NetCommRunnable;
import com.horizon.utils.conn.GroupConnection;
import com.horizon.utils.conn.SingleConnection;
import com.horizon.utils.routine.Routine;

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

    /**
     * A string for assembling a Toast msg
     */
    public static String toastText = "";

    /**
     * A boolean var to enable debug skipping on net reliability.
     */
    public static boolean DEBUG = true;

    public static Routine routine = null;
}
