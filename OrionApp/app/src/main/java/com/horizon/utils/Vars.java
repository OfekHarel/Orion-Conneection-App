package com.horizon.utils;

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
    public static  SingleConnection connection = new SingleConnection("", "");
}
