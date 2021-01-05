package com.horizon.utils.routine;

import com.horizon.utils.conn.SingleConnection;
import java.util.Date;

public class Routine {

  private String[] actions;
  private String name;
  private Date time;
  private SingleConnection singleConnection;

  public Routine(
    String[] actions,
    String name,
    Date time,
    SingleConnection singleConnection
  ) {
    this.actions = actions;
    this.name = name;
    this.time = time;
    this.singleConnection = singleConnection;
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

  public SingleConnection getSingleConnection() {
    return singleConnection;
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

  public void setSingleConnection(SingleConnection singleConnection) {
    this.singleConnection = singleConnection;
  }
}
