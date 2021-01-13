package com.horizon.utils.routine;

import com.horizon.utils.conn.Connection;

/**
 * A class to represent the Routine object type.
 */
public class Routine {

  private String action;
  private String name;
  private Time time;
  private Connection connection;

  public Routine(String action, String name, Time time, Connection connection) {
    this.action = action;
    this.name = name;
    this.time = time;
    this.connection = connection;
  }

  public String getActions() {
    return action;
  }

  public String getName() {
    return name;
  }

  public Connection getSingleConnection() {
    return connection;
  }

  public void setActions(String action) {
    this.action = action;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Time getTime() {
    return time;
  }
}
