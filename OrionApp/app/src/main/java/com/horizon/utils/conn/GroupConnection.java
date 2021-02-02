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

    /**
     * @param name - the name of the group
     * @param list - a list of {@link com.horizon.utils.conn.SingleConnection}
     *             that will be inside the group
     */
    public GroupConnection(String name, ArrayList<SingleConnection> list) {
        this.name = name;
        this.list = list;
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * @param name - the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the list of the single connections
     */
    public ArrayList<SingleConnection> getList() {
        return list;
    }

    /**
     * @param list - the list to set
     */
    public void setList(ArrayList<SingleConnection> list) {
        this.list = list;
    }

    /**
     * clears the object
     */
    public void clear() {
        this.name = "";
        this.list.clear();
    }

    /**
     * @param c - the connection to add
     */
    public void add(SingleConnection c) {
        this.list.add(c);
    }

    /**
     * @return the group connection devices
     */
    public ArrayList<String> get() {
        ArrayList<String> arr = new ArrayList<>();
        for (int i = 0; i < this.list.size(); i++) {
            arr.add(this.list.get(i).getName());
        }
        return arr;
    }

    /**
     * checks if a name of connection is in the group.
     * @param name - the name to check.
     * @return if it exist or not.
     */
    public boolean isExist(String name) {
        for (int i = 0; i < this.list.size(); i++) {
            if (name.equals(this.list.get(i).getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * activated a routine to all of the group devices.
     */
    public void routine() {
        for (int i = 0; i < this.list.size(); i++) {
            NetRunnableFactory.passAnAction(this.list.get(i).getName(), Actions.ROUTINE);
        }
    }

    /**
     * send an action to all of the group devices
     * @param action - the action to send
     */
    public void send(Actions action) {
        for (int i = 0; i < this.list.size(); i++) {
            this.list.get(i).getRunnable().setAction(action);
            NetRunnableFactory.passAnAction(this.list.get(i).getName(), action);
        }
    }
}
