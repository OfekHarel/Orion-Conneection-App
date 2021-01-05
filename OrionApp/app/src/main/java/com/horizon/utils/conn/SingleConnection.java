package com.horizon.utils.conn;

public class SingleConnection {

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
