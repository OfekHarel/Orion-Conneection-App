package com.horizon.utils;

public class SingleConnection implements Connection {

    private String name;
    private boolean isGroup;
    private String id;

    public SingleConnection(String name, String id) {
        this.id = id;
        this.name = name;
        this.isGroup = false;
    }

    public SingleConnection(String name) {
        this.name = name;
        this.isGroup = true;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getID() {
        return id;
    }

    public boolean isGroup() {
        return isGroup;
    }
}
