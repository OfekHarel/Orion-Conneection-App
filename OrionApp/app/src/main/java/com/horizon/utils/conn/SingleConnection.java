package com.horizon.utils.conn;

import com.horizon.networking.NetCommRunnable;
import com.horizon.networking.NetRunnableFactory;

/**
 * A bond to represent a connection between one comp to an app.
 */
public class SingleConnection extends Connection {

  private final String name;
  private final String id;

  private Thread thread;
  private NetCommRunnable runnable;

  public SingleConnection(String name, String id) {
    this.id = id;
    this.name = name;
    this.runnable = NetRunnableFactory.get(name);
  }

  public String getName() {
    return name;
  }

  public String getID() {
    return id;
  }

  public void initConnection() {
    this.runnable.pair(this.id, this.name);
    this.thread = new Thread(this.runnable);
    thread.start();
  }

  public NetCommRunnable getRunnable() {
    return NetRunnableFactory.get(name);
  }

  public Thread getThread() {
    return thread;
  }
}
