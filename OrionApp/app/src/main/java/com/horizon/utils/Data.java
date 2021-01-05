package com.horizon.utils;

import java.util.ArrayList;

public class Data {
    public static Data inst = null;
    private static ArrayList<SingleConnection> singleConnections;
    private static ArrayList<Routine> routines;

    private Data() {}

    public static Data getInstance() {
        if (inst == null) {
            inst = new Data();
            singleConnections = new ArrayList<SingleConnection>();
            routines = new ArrayList<Routine>();
        }
        return inst;
    }

    public ArrayList<SingleConnection> getConnections() {
        return singleConnections;
    }

    public void addConnection(SingleConnection c) {
        singleConnections.add(c);
    }

    public SingleConnection getConnectionName(String name) {
        for (int i = 0; i < singleConnections.size(); i++) {
            if (singleConnections.get(i).getName().equals(name)) {
                return singleConnections.get(i);
            }
        }
        return null;
    }

    public SingleConnection getConnectionByID(String id) {
        for (int i = 0; i < singleConnections.size(); i++) {
            if (singleConnections.get(i).getID().equals(id)) {
                return singleConnections.get(i);
            }
        }
        return null;
    }

    public static ArrayList<Routine> getRoutines() { return routines; }
    public void addRoutine(Routine r) { routines.add(r); }

}
