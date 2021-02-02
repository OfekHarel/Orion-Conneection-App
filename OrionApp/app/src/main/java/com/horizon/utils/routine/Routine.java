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

  private final String action;
  private String name;
  private final String devName;
  private final Time time;
  private SingleConnection singleConnection;
  private GroupConnection groupConnection;
  private final boolean isGroup;

  /**
   * @param action - the action of the routine
   * @param name - the name of the routine
   * @param time - the time of the routine
   * @param singleConnection - the single connection of the routine
   */
  public Routine(String action, String name, Time time, SingleConnection singleConnection) {
    this.action = action;
    this.name = name;
    this.time = time;
    this.singleConnection = singleConnection;
    this.devName = singleConnection.getName();
    this.isGroup = false;
  }

  /**
   * @param action - the action of the routine
   * @param name - the name of the routine
   * @param time - the time of the routine
   * @param groupConnection - the group connection of the routine
   */
  public Routine(String action, String name, Time time, GroupConnection groupConnection) {
    this.action = action;
    this.name = name;
    this.time = time;
    this.groupConnection = groupConnection;
    this.devName = groupConnection.getName();
    this.isGroup = true;
  }

  /**
   * @return - the action of the routine
   */
  public String getAction() {
    return action;
  }

  /**
   * @return the routine name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name - the name of the routine to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the time that the routine is set to work on
   */
  public Time getTime() {
    return time;
  }

  /**
   * @return the dev name that the routine is set on (Group or Single)
   */
  public String getDevName() {
    return devName;
  }

  /**
   * This function is responsible of handling a routine deletion.
   */
  public void kill() {
    Executioner.Actions.DEL_ROUTINE.setStr(NetworkPackets.assamble("DROUT", getName()));
    if (this.isGroup) {
      groupConnection.send(Executioner.Actions.DEL_ROUTINE);
    } else {
      NetRunnableFactory.passAnAction(getDevName(), Executioner.Actions.DEL_ROUTINE);
    }
  }
}
