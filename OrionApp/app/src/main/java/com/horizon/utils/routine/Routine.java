package com.horizon.utils.routine;

import com.horizon.utils.conn.Connection;
import java.util.Date;

public class Routine {

  private String[] actions;
  private String name;
  private Date time;
  private String [] days;
  private Connection connection;

  public Routine(
    String[] actions,
    String name,
    Date time,
    Connection connection,
    String[] days
  ) {
    this.actions = actions;
    this.name = name;
    this.time = time;
    this.connection = connection;
    this.days = days;
  }

  public String[] getActions() {
    return actions;
  }

  public String getName() {
    return name;
  }

  public Date getTime() {
    return time;
  }

  public Connection getSingleConnection() {
    return connection;
  }

  public void setActions(String[] actions) {
    this.actions = actions;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setTime(Date time) {
    this.time = time;
  }
}
