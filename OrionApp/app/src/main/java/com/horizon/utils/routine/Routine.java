package com.horizon.utils.routine;

import com.horizon.utils.conn.Connection;
import com.horizon.utils.conn.GroupConnection;
import com.horizon.utils.conn.SingleConnection;

/**
 * A class to represent the Routine object type.
 */
public class Routine {

  private String action;
  private String name;
  private Time time;
  private SingleConnection singleConnection;
  private GroupConnection groupConnection;

  public Routine(String action, String name, Time time, SingleConnection singleConnection) {
    this.action = action;
    this.name = name;
    this.time = time;
    this.singleConnection = singleConnection;
  }

  public Routine(String action, String name, Time time, GroupConnection groupConnection) {
    this.action = action;
    this.name = name;
    this.time = time;
    this.groupConnection = groupConnection;
  }

  public String getActions() {
    return action;
  }

  public String getName() {
    return name;
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
