package com.horizon.utils.conn;

import java.util.ArrayList;

public class GroupConnection extends Connection {
    private String name;
    private ArrayList<SingleConnection> list;

    public GroupConnection(String name, ArrayList<SingleConnection> list) {
        this.name = name;
        this.list = list;
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

    public void add(SingleConnection c)  {
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
}
