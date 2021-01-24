package com.horizon.utils.conn;

import com.horizon.networking.NetCommRunnable;
import com.horizon.networking.NetRunnableFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * A bond to represent a connection between one comp to an app.
 */
public class SingleConnection extends Connection {

  private final String name;
  private final String id;

  private Thread thread;

  public SingleConnection(String name, String id) {
    this.id = id;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public String getID() {
    return id;
  }

  public boolean initConnection() {
    Future<Boolean> is = Executors.newSingleThreadExecutor().submit(() -> getRunnable().pair(id, name));
    try {
      return is.get();
    } catch (ExecutionException | InterruptedException e) {
      e.printStackTrace();
      return false;
    }
  }

  public void flowConnection() {
    thread = new Thread(getRunnable());
    thread.start();
  }

  public NetCommRunnable getRunnable() {
    return NetRunnableFactory.get(name);
  }
}
