package com.horizon.utils;

import android.util.Pair;

import com.horizon.networking.Executioner.Actions;
import com.horizon.networking.NetCommRunnable;
import com.horizon.utils.conn.GroupConnection;
import com.horizon.utils.conn.SingleConnection;

import java.util.ArrayList;

/**
 * A class that contains a reusable temp vars.
 */
public class Vars {
    public static boolean isFromGroup = false;
    public static GroupConnection newGroup = new GroupConnection("",
            new ArrayList<SingleConnection>());
    public static SingleConnection connection = null;
    public static int actions = 0;
    public static ArrayList<Pair<String, NetCommRunnable>> names = new ArrayList<>();

}
