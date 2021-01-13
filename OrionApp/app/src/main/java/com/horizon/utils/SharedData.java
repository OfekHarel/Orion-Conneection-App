package com.horizon.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.reflect.TypeToken;
import com.horizon.utils.conn.Connection;
import com.horizon.utils.conn.GroupConnection;

import com.google.gson.Gson;
import com.horizon.utils.conn.SingleConnection;
import com.horizon.utils.routine.Routine;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class SharedData {
    private static SharedData instance = null;

    /*
     * Entry names
     */
    private final String NAME = "mem";
    private final String SINGLE_CONNS = "singleConns";
    private final String GROUP_CONNS = "groupConns";
    private final String ROUTINES = "routines";

    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;
    private final Gson gson;


    /**
     * Open the shared memory and its entries.
     * @param context the context which it called from
     */
    @SuppressLint("CommitPrefEdits")
    private SharedData(Context context) {
        this.sharedPreferences = context.getSharedPreferences(this.NAME, Context.MODE_PRIVATE);
        this.editor = this.sharedPreferences.edit();
        this.gson = new Gson();

        final String FIRST_TIME = "firstTime";
        boolean isFirstTime = this.sharedPreferences.getBoolean(FIRST_TIME, true);


        if (isFirstTime) {
            this.editor.putBoolean(FIRST_TIME, false);
            this.editor.putString(this.SINGLE_CONNS, null);
            this.editor.putString(this.GROUP_CONNS, null);
            this.editor.putString(this.ROUTINES, null);
            this.editor.apply();
        }
    }

    /**
     *
     * @param context the context which it called from
     * @return The instance
     */
    public static SharedData getInstance(Context context) {
        if (instance ==  null) {
            instance = new SharedData(context);
        }
        return instance;
    }

    /**
     * Sets the single connections entry.
     * @param connections The new val to set to.
     */
    public void setSingleConnections(ArrayList<SingleConnection> connections) {
        String json = this.gson.toJson(connections);
        this.editor.putString(this.SINGLE_CONNS, json);
        this.editor.apply();
    }

    /**
     * Adds a connection to the shared mem.
     * @param connection - to connection to add
     */
    public void addSingleConnection(SingleConnection connection) {
        ArrayList<SingleConnection> arrayList = getSingleConnections();
        arrayList.add(connection);
        this.setSingleConnections(arrayList);
    }

    /**
     * @return The single connection list.
     */
    public ArrayList<SingleConnection> getSingleConnections() {
        String json = this.sharedPreferences.getString(this.SINGLE_CONNS, null);
        Type type = new TypeToken<ArrayList<SingleConnection>>() {}.getType();
        ArrayList<SingleConnection> conns = this.gson.fromJson(json, type);
        if (conns == null) {
            conns = new ArrayList<SingleConnection>();
        }
        return conns;
    }

    /**
     * Sets the group connections entry.
     * @param connections The new val to set to.
     */
    public void setGroupConnections(ArrayList<GroupConnection> connections) {
        String json = this.gson.toJson(connections);
        this.editor.putString(this.GROUP_CONNS, json);
        this.editor.apply();
    }

    /**
     * Adds a connection to the shared mem.
     * @param connection - connection to connection to add
     * @param toAddSingle - whether to add the connections to the single one as well.
     */
    public void addGroupConnection(GroupConnection connection, boolean toAddSingle) {
        ArrayList<GroupConnection> arrayList = getGroupConnections();
        arrayList.add(connection);
        this.setGroupConnections(arrayList);

        if(toAddSingle) {
            addGroupToSingles(connection);
        }
    }

    /**
     * Adds a connection to the shared mem.
     * by default, ads them to the single one.
     * @param connection - connection to connection to add
     */
    public void addGroupConnection(GroupConnection connection) {
        this.addGroupConnection(connection, true);
    }

    /**
     * @param groupConnection group connection to add.
     */
    public void addGroupToSingles(GroupConnection groupConnection) {
        ArrayList<SingleConnection> arrayList = getSingleConnections();
        arrayList.addAll(groupConnection.getList());
        setSingleConnections(arrayList);
    }

    /**
     * @return The group connection list.
     */
    public ArrayList<GroupConnection> getGroupConnections() {
        String json = this.sharedPreferences.getString(this.GROUP_CONNS, null);
        Type type = new TypeToken<ArrayList<GroupConnection>>() {}.getType();
        ArrayList<GroupConnection> conns = this.gson.fromJson(json, type);
        return conns == null? new ArrayList<GroupConnection>() : conns;
    }

    /**
     * @return The group connection name as a String list.
     */
    public ArrayList<String> getGroupsAsStringArr() {
        ArrayList<String> arr = new ArrayList<>();
        ArrayList<GroupConnection> conns = getGroupConnections();
        for (int i = 0; i < conns.size(); i++) {
            arr.add(conns.get(i).getName());
        }
        return arr;
    }

    /**
     * @return The single connection name as a String list.
     */
    public ArrayList<String> getSinglesAsStringArr() {
        ArrayList<String> arr = new ArrayList<>();
        ArrayList<SingleConnection> conns = getSingleConnections();
        for (int i = 0; i < conns.size(); i++) {
            arr.add(conns.get(i).getName());
        }
        return arr;
    }

    /**
     * @param idInput the id to check.
     * @return The Single Connection that own the same id as the val, if not Null.
     */
    public SingleConnection getSingleConnectionByID(String idInput) {
        ArrayList<SingleConnection> conns = getSingleConnections();
        for (int i = 0; i < conns.size(); i++) {
            if(idInput == conns.get(i).getID()) {
                return conns.get(i);
            }
        }
        return null;
    }

    /**
     * @param nameInput the name to check.
     * @return The Single Connection that own the same name as the val, if not Null.
     */
    public SingleConnection getSingleConnectionByName(String nameInput) {
        return getSingleConnectionByName(nameInput, getSingleConnections());
    }

    /**
     * @param nameInput the name to check.
     * @param arr The arr to look
     * @return The Single Connection that own the same name as the val, if not Null.
     */
    public SingleConnection getSingleConnectionByName(String nameInput, ArrayList<SingleConnection> arr) {
        for (int i = 0; i < arr.size(); i++) {
            if(nameInput.equals(arr.get(i).getName())) {
                return arr.get(i);
            }
        }
        return null;
    }

    /**
     * @param nameInput the id to check.
     * @return The Group Connection that own the same name as the val, if not Null.
     */
    public GroupConnection getGroupConnectionByName(String nameInput) {
        ArrayList<GroupConnection> conns = getGroupConnections();
        for (int i = 0; i < conns.size(); i++) {
            if(nameInput.equals(conns.get(i).getName())) {
                return conns.get(i);
            }
        }
        return null;
    }

    /**
     * cleans the single entry
     */
    public void cleanSingle() {
        this.editor.remove(SINGLE_CONNS);
        this.editor.putString(SINGLE_CONNS, null);
        this.editor.apply();
    }

    /**
     * cleans the group entry
     */
    public void cleanGroups() {
        this.editor.remove(GROUP_CONNS);
        this.editor.putString(GROUP_CONNS, null);
        this.editor.apply();
    }

    /**
     * loads the entries
     */
    public void load() {
        ArrayList<GroupConnection> loadGroup = getGroupConnections();
        ArrayList<SingleConnection> loadSingle = getSingleConnections();
    }

    @Override
    public String toString() {
        return "[Single: " + getSingleConnections().toString() +
                "Groups:" + getGroupConnections().toString() +
                "]";
    }

    /**
     * Sets the routines entry.
     * @param routines The new val to set to.
     */
    public void setRoutines(ArrayList<Routine> routines) {
        String json = this.gson.toJson(routines);
        this.editor.putString(this.ROUTINES, json);
        this.editor.apply();
    }

    /**
     * Adds a routine to the shared mem.
     * @param routine - to connection to add
     */
    public void addRoutines(Routine routine) {
        ArrayList<Routine> arrayList = getRoutines();
        arrayList.add(routine);
        this.setRoutines(arrayList);
    }

    /**
     * @return The routine list.
     */
    public ArrayList<Routine> getRoutines() {
        String json = this.sharedPreferences.getString(this.ROUTINES, null);
        Type type = new TypeToken<ArrayList<Routine>>() {}.getType();
        ArrayList<Routine> routines = this.gson.fromJson(json, type);
        if (routines == null) {
            routines = new ArrayList<Routine>();
        }
        return routines;
    }

    public ArrayList<String> getAllConnectionAsString() {
        ArrayList<String> arr = getSinglesAsStringArr();
        arr.addAll(getGroupsAsStringArr());
        return arr;
    }

    public Connection getConnectionByName(String name) {
        GroupConnection g = getGroupConnectionByName(name);
        SingleConnection s = getSingleConnectionByName(name);

        if (g != null && s != null) {
            return null;
        }
        else if (s != null) {
            return s;
        } else return g;
    }

    /**
     * @return The routines name as a String list.
     */
    public ArrayList<String> getRoutinesAsArrayString() {
        ArrayList<String> arr = new ArrayList<>();
        ArrayList<Routine> conns = getRoutines();
        for (int i = 0; i < conns.size(); i++) {
            arr.add(conns.get(i).getName());
        }
        return arr;
    }

    public void cleanRoutines() {
        this.editor.remove(ROUTINES);
        this.editor.putString(ROUTINES, null);
        this.editor.apply();
    }
}
