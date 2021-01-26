package com.horizon.utils.routine;

import com.horizon.networking.Executioner;
import com.horizon.networking.NetRunnableFactory;
import com.horizon.networking.NetworkPackets;
import com.horizon.utils.conn.GroupConnection;
import com.horizon.utils.conn.SingleConnection;

/**
 * A class to represent the Routine object type.
 */
public class Routine {

  private String action;
  private String name;
  private String devName;
  private final Time time;
  private SingleConnection singleConnection;
  private GroupConnection groupConnection;
  private boolean isGroup;

  public Routine(String action, String name, Time time, SingleConnection singleConnection) {
    this.action = action;
    this.name = name;
    this.time = time;
    this.singleConnection = singleConnection;
    this.devName = singleConnection.getName();
    this.isGroup = false;
  }

  public Routine(String action, String name, Time time, GroupConnection groupConnection) {
    this.action = action;
    this.name = name;
    this.time = time;
    this.groupConnection = groupConnection;
    this.devName = groupConnection.getName();
    this.isGroup = true;
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

  public String getDevName() {
    return devName;
  }

  public void kill() {
    Executioner.Actions.DEL_ROUTINE.setStr(NetworkPackets.assamble("DROUT", getName()));
    if (this.isGroup) {
      groupConnection.send(Executioner.Actions.DEL_ROUTINE);
    } else {
      NetRunnableFactory.passAnAction(getDevName(), Executioner.Actions.DEL_ROUTINE);
    }

  }
}
