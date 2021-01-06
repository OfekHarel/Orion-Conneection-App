package com.horizon.utils;

import android.util.Log;

import com.horizon.OrionConnection.Groups;
import com.horizon.utils.conn.GroupConnection;
import com.horizon.utils.conn.SingleConnection;
import com.horizon.utils.routine.Routine;
import java.util.ArrayList;

/**
 *
 */
public class Data {

  public static Data inst = null;
  private static ArrayList<SingleConnection> connections;
  private static ArrayList<GroupConnection> groupConnections;
  private static ArrayList<Routine> routines;

  private Data() {}

  public static Data getInstance() {
    if (inst == null) {
      inst = new Data();
      connections = new ArrayList<SingleConnection>();
      groupConnections = new ArrayList<GroupConnection>();
      routines = new ArrayList<Routine>();
    }
    return inst;
  }

  public ArrayList<SingleConnection> getConnections() {
    return connections;
  }

  public ArrayList<GroupConnection> getGroupConnections() {
    return groupConnections;
  }

  public void addConnection(SingleConnection c) {
    connections.add(c);
  }

  public void addConnection(GroupConnection c) { groupConnections.add(c); }

  public void addConnections(GroupConnection c) { connections.addAll(c.getList()); }

  public SingleConnection getConnectionName(String name) {
    for (int i = 0; i < connections.size(); i++) {
      if (connections.get(i).getName().equals(name)) {
        return connections.get(i);
      }
    }
    return null;
  }

  public GroupConnection getGroupConnectionName(String name) {
    for (int i = 0; i < groupConnections.size(); i++) {
      if (groupConnections.get(i).getName().equals(name)) {
        return groupConnections.get(i);
      }
    }
    return null;
  }

  public SingleConnection getConnectionByID(String id) {
    for (int i = 0; i < connections.size(); i++) {
      if (connections.get(i).getID().equals(id)) {
        return connections.get(i);
      }
    }
    return null;
  }

  public void eraseConnections() {
    connections.clear();;
  }

  public ArrayList<String> getGroupsAsStringArr() {
    ArrayList<String> arr = new ArrayList<>();
    for (int i = 0; i < groupConnections.size(); i++) {
      arr.add(groupConnections.get(i).getName());
      Log.i("BLLLLLLLLLaaahh", groupConnections.get(i).getName());
    }
    return arr;
  }
}
