package com.horizon.utils.conn;

import com.horizon.networking.Executioner.Actions;
import com.horizon.networking.NetRunnableFactory;

import java.util.ArrayList;

/**
 * A bunch of single connections that shown as one to control them as if they
 * were one.
 */
public class GroupConnection extends Connection {
    private String name;
    private ArrayList<SingleConnection> list;

    public GroupConnection(String name, ArrayList<SingleConnection> list) {
        this.name = name;
        this.list = list;
    }

    public GroupConnection() {
        this.name = "";
        this.list = new ArrayList<SingleConnection>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<SingleConnection> getList() {
        return list;
    }

    public void setList(ArrayList<SingleConnection> list) {
        this.list = list;
    }

    public void clear() {
        this.name = "";
        this.list.clear();
    }

    public void add(SingleConnection c) {
        this.list.add(c);
    }

    public ArrayList<String> get() {
        ArrayList<String> arr = new ArrayList<>();
        for (int i = 0; i < this.list.size(); i++) {
            arr.add(this.list.get(i).getName());
        }
        return arr;
    }

    public boolean isExist(String name) {
        for (int i = 0; i < this.list.size(); i++) {
            if (name.equals(this.list.get(i).getName())) {
                return true;
            }
        }
        return false;
    }

    public void routine() {
        for (int i = 0; i < this.list.size(); i++) {
            NetRunnableFactory.passAnAction(this.list.get(i).getName(), Actions.ROUTINE);
        }
    }

    public void send(Actions action) {
        for (int i = 0; i < this.list.size(); i++) {
            this.list.get(i).getRunnable().setAction(action);
            NetRunnableFactory.passAnAction(this.list.get(i).getName(), action);
        }
    }
}
