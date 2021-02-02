package com.horizon.utils.conn;

import com.horizon.networking.NetCommRunnable;
import com.horizon.networking.NetRunnableFactory;
import com.horizon.networking.NetworkPackets;
import com.horizon.utils.Vars;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * A bond to represent a connection between one comp to an app.
 */
public class SingleConnection extends Connection {

  private final String name;
  private final String id;
  private  String[] info = null;
  private Thread thread;

  /**
   * @param name - The name of the connection
   * @param id - the ID of the computer
   */
  public SingleConnection(String name, String id) {
    this.id = id;
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  /**
   * @return the connection's ID.
   */
  public String getID() {
    return id;
  }

  /**
   * @return whether the connection is successfully initiated.
   */
  public boolean initConnection() {
    Future<Boolean> is = Executors.newSingleThreadExecutor().submit(() -> getRunnable().pair(id, name));
    try {
      if(is.get()) {
        String[] arr = NetworkPackets.split(Vars.msg);
        setInfo(Arrays.copyOfRange(arr, 1,arr.length));
        Vars.msg = "";
      }
      return is.get();
    } catch (ExecutionException | InterruptedException e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Starts the runnable thread of the flow incoming and outgoing msg.
   */
  public void flowConnection() {
    thread = new Thread(getRunnable());
    thread.start();
  }

  /**
   * @return the runnable that this connection is connected to.
   */
  public NetCommRunnable getRunnable() {
    return NetRunnableFactory.get(name);
  }

  /**
   * @param info - the specs info of the connection
   */
  public void setInfo(String[] info) {
    this.info = info;
  }

  /**
   * @return the specs info
   */
  public String[] getInfo() {
    return info;
  }
}
