package com.horizon.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.horizon.utils.conn.GroupConnection;

import com.google.gson.Gson;
import com.horizon.utils.conn.SingleConnection;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class SharedData {
    private static SharedData instance = null;

    private final String NAME = "mem";
    private final String SINGLE_CONNS = "singleConns";
    private final String GROUP_CONNS = "groupConns";
    private final String FIRST_TIME = "firstTime";

    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;
    private final Gson gson;

    private boolean isFirstTime;

    @SuppressLint("CommitPrefEdits")
    private SharedData(Context context) {
        this.sharedPreferences = context.getSharedPreferences(this.NAME, Context.MODE_PRIVATE);
        this.editor = this.sharedPreferences.edit();
        this.gson = new Gson();

        this.isFirstTime = this.sharedPreferences.getBoolean(this.FIRST_TIME, true);


        if (this.isFirstTime) {
            this.editor.putBoolean(this.FIRST_TIME, false);
            this.editor.putString(this.SINGLE_CONNS, null);
            this.editor.putString(this.GROUP_CONNS, null);
            this.editor.apply();
        }
    }

    public static SharedData getInstance(Context c) {
        if (instance ==  null) {
            instance = new SharedData(c);
        }
        return instance;
    }

    public ArrayList<GroupConnection> getGroupConnections() {
        String json = this.sharedPreferences.getString(this.GROUP_CONNS, null);
        Type type = new TypeToken<ArrayList<GroupConnection>>() {}.getType();
        ArrayList<GroupConnection> conns = this.gson.fromJson(json, type);
        return conns == null? new ArrayList<GroupConnection>() : conns;
    }

    public ArrayList<String> getGroupsAsStringArr() {
        ArrayList<String> arr = new ArrayList<>();
        ArrayList<GroupConnection> conns = getGroupConnections();
        for (int i = 0; i < conns.size(); i++) {
            arr.add(conns.get(i).getName());
        }
        return arr;
    }

    public ArrayList<SingleConnection> getSingleConnections() {
        String json = this.sharedPreferences.getString(this.SINGLE_CONNS, null);
        Type type = new TypeToken<ArrayList<SingleConnection>>() {}.getType();
        ArrayList<SingleConnection> conns = this.gson.fromJson(json, type);
        if (conns == null) {
            conns = new ArrayList<SingleConnection>();
        }
        return conns;
    }

    public void setSingleConnections(ArrayList<SingleConnection> connections) {
        String json = this.gson.toJson(connections);
        this.editor.putString(this.SINGLE_CONNS, json);
        this.editor.apply();
    }

    public void addSingleConnection(SingleConnection connection) {
        ArrayList<SingleConnection> arrayList = getSingleConnections();
        arrayList.add(connection);
        this.setSingleConnections(arrayList);
    }
    
    public void load() {
        ArrayList<GroupConnection> loadGroup = getGroupConnections();
        ArrayList<SingleConnection> loadSingle = getSingleConnections();
    }

    public SingleConnection getSingleConnectionByID(String idInput) {
        ArrayList<SingleConnection> conns = getSingleConnections();
        for (int i = 0; i < conns.size(); i++) {
            if(idInput == conns.get(i).getID()) {
                return conns.get(i);
            }
        }
        return null;
    }

    public SingleConnection getSingleConnectionByName(String nameInput) {
        ArrayList<SingleConnection> conns = getSingleConnections();
        for (int i = 0; i < conns.size(); i++) {
            if(nameInput == conns.get(i).getName()) {
                return conns.get(i);
            }
        }
        return null;
    }

    public void cleanSingle() {
        this.editor.remove(SINGLE_CONNS);
        this.editor.putString(SINGLE_CONNS, null);
        this.editor.apply();
    }

    public void cleanGroups() {
        this.editor.remove(GROUP_CONNS);
        this.editor.putString(GROUP_CONNS, null);
        this.editor.apply();
    }

    public GroupConnection getGroupConnectionByName(String nameInput) {
        ArrayList<GroupConnection> conns = getGroupConnections();
        for (int i = 0; i < conns.size(); i++) {
            if(nameInput == conns.get(i).getName()) {
                return conns.get(i);
            }
        }
        return null;
    }

    public void addGroupToSingles(GroupConnection groupConnection) {
        ArrayList<SingleConnection> arrayList = getSingleConnections();
        arrayList.addAll(groupConnection.getList());
        setSingleConnections(arrayList);
    }

    public void setGroupConnections(ArrayList<GroupConnection> connections) {
        String json = this.gson.toJson(connections);
        this.editor.putString(this.GROUP_CONNS, json);
        this.editor.apply();
    }

    public void addGroupConnection(GroupConnection connection) {
        ArrayList<GroupConnection> arrayList = getGroupConnections();
        arrayList.add(connection);
        this.setGroupConnections(arrayList);
    }

    @Override
    public String toString() {
        return "[Single: " + getSingleConnections().toString() +
                "Groups:" + getGroupConnections().toString() +
                "]";
    }
}
