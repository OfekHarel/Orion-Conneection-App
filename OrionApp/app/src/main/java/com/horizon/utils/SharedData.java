package com.horizon.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.util.Pair;

import com.google.gson.reflect.TypeToken;
import com.horizon.networking.NetCommRunnable;
import com.horizon.networking.NetRunnableFactory;
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
    private final String RUNNABLE_NAMES = "run";

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
            this.editor.putString(this.RUNNABLE_NAMES, null);
            this.editor.apply();
        }
        setSingleConnections(NetRunnableFactory.buildFromBlueprints(getSingleConnections()));
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

    public static SharedData getInstance() {
        return instance;
    }


    /**
     * Sets the single connections entry.
     * @param runs The new val to set to.
     */
    public void setRunName(ArrayList<Pair<String, NetCommRunnable>> runs) {
        String json = this.gson.toJson(runs);
        this.editor.putString(this.RUNNABLE_NAMES, json);
        this.editor.apply();
    }

    /**
     * Adds a connection to the shared mem.
     * @param run - to connection to add
     */
    public void addRun(Pair<String, NetCommRunnable> run) {
        ArrayList<Pair<String, NetCommRunnable>> arrayList = getRuns();
        arrayList.add(run);
        this.setRunName(arrayList);

    }

    /**
     * @return The single connection list.
     */
    public ArrayList<Pair<String, NetCommRunnable>> getRuns() {
        String json = this.sharedPreferences.getString(this.RUNNABLE_NAMES, null);
        Type type = new TypeToken<ArrayList<Pair<String, NetCommRunnable>>>() {}.getType();
        ArrayList<Pair<String, NetCommRunnable>> runs = this.gson.fromJson(json, type);
        if (runs == null) {
            runs = new ArrayList<>();
        }
        return runs;
    }

    /**
     * Sets the single connections entry.
     * @param connections The new val to set to.
     */
    public void setSingleConnections(ArrayList<SingleConnection> connections) {
        if (connections == null) {
            return;
        }

        String json = this.gson.toJson(connections);
        this.editor.putString(this.SINGLE_CONNS, json);
        this.editor.apply();

        ArrayList<Pair<String, NetCommRunnable>> runs = getRuns(), arr = new ArrayList<>();
        for (int i = 0; i < runs.size(); i++) {
            for (int j = 0; j < connections.size(); j++) {
                if (runs.get(i).first.equals(connections.get(j).getName())) {
                    arr.add(runs.get(i));
                }
            }
        }
        setRunName(arr);
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
     * @param nameInput the name to check.
     * @return The Single Connection that own the same name as the val, if not Null.
     */
    public GroupConnection getGroupConnectionByName(String nameInput) {
        return getGroupConnectionByName(nameInput, getGroupConnections());
    }


    /**
     * @param nameInput the id to check.
     * @return The Group Connection that own the same name as the val, if not Null.
     */
    public GroupConnection getGroupConnectionByName(String nameInput, ArrayList<GroupConnection> arr) {
        for (int i = 0; i < arr.size(); i++) {
            if(nameInput.equals(arr.get(i).getName())) {
                return arr.get(i);
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
     * cleans the Routine entry
     */
    public void cleanRoutines() {
        this.editor.remove(ROUTINES);
        this.editor.putString(ROUTINES, null);
        this.editor.apply();
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

    /**
     * Deletes the provided Single Connections 
     * @param toDelete -
     */
    public void cleanSingle(ArrayList<SingleConnection> toDelete) {
        ArrayList<SingleConnection> arr = getSingleConnections();
        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < toDelete.size(); j++) {
                if (arr.get(i).getID().equals(toDelete.get(j).getID())) {
                    arr.remove(i);
                }
            }
        }
        setSingleConnections(arr);
    }

     /**
     * Deletes the provided Group Connections 
     * @param toDelete -
     */
    public void cleanGroups(ArrayList<GroupConnection> toDelete) {
        ArrayList<GroupConnection> arr = getGroupConnections();
        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < toDelete.size(); j++) {
                if (arr.get(i).getName().equals(toDelete.get(j).getName())) {
                    arr.remove(i);
                }
            }
        }
        setGroupConnections(arr);
    }

    /**
    * Deletes the provided routines
    * @param toDelete -
    */
    public void cleanRoutines(ArrayList<Routine> toDelete) {
        ArrayList<Routine> arr = getRoutines();
        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < toDelete.size(); j++) {
                if (arr.get(i).getName().equals(toDelete.get(j).getName())) {
                    arr.remove(i);
                }
            }
        }
        setRoutines(arr);
    }

    /**
     * 
     * @param nameInput The name to check
     * @param arr The arr to check
     * @return The Single Connection that own the same name as the val, if not Null.
     */
    public Routine getRoutineConnectionByName(String nameInput, ArrayList<Routine> arr) {
        for (int i = 0; i < arr.size(); i++) {
            if(nameInput.equals(arr.get(i).getName())) {
                return arr.get(i);
            }
        }
        return null;
    }

    /**
     * @param nameInput the name to check.
     * @return The Single Connection that own the same name as the val, if not Null.
     */
    public Routine getRoutineConnectionByName(String nameInput) {
        return getRoutineConnectionByName(nameInput, getRoutines());
    }
}
