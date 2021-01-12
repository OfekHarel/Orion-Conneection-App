package com.horizon.utils.conn;

/**
 * A bond to represent a connection between one comp to an app.
 */
public class SingleConnection extends Connection {

  private String name;
  private boolean isGroup;
  private String id;

  public SingleConnection(String name, String id) {
    this.id = id;
    this.name = name;
    this.isGroup = false;
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
