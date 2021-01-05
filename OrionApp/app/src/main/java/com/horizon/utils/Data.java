package com.horizon.utils;

import com.horizon.utils.conn.SingleConnection;
import com.horizon.utils.routine.Routine;
import java.util.ArrayList;

public class Data {

  public static Data inst = null;
  private static ArrayList<SingleConnection> connections;
  private static ArrayList<Routine> routines;

  private Data() {}

  public static Data getInstance() {
    if (inst == null) {
      inst = new Data();
      connections = new ArrayList<SingleConnection>();
      routines = new ArrayList<Routine>();
    }
    return inst;
  }

  public ArrayList<SingleConnection> getConnections() {
    return connections;
  }

  public void addConnection(SingleConnection c) {
    connections.add(c);
  }

  public SingleConnection getConnectionName(String name) {
    for (int i = 0; i < connections.size(); i++) {
      if (connections.get(i).getName().equals(name)) {
        return connections.get(i);
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

  public ArrayList<String> getConnectionsAsStrings() {
    ArrayList<String> arrayList = new ArrayList<>();
    for (int i = 0; i < getConnections().size(); i++) {
      arrayList.add(connections.get(i).getName());
    }
    return arrayList;
  }

  public ArrayList<Routine> getRoutines() {
    return routines;
  }

  public void addRoutine(Routine r) {
    routines.add(r);
  }

  public void eraseConnections() {
    connections.clear();
  }
}
