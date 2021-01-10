package com.horizon.OrionConnection;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.horizon.networking.NetCommRunnable;
import com.horizon.utils.SharedData;
import com.horizon.utils.conn.ConnectionListAdapter;

public class MainActivity extends BaseOrionActivity {

  private ListView listView; // List view of the device list.
  private ConnectionListAdapter listadpt;

  public static Thread t = new Thread(new NetCommRunnable());

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    this.menu = findViewById(R.id.drawer);
    SharedData.getInstance(this).load();

    /*
     * List view init.
     */
    this.listView = findViewById(R.id.main_list);
    this.listadpt =
      new ConnectionListAdapter(
        this,
        R.layout.single_conn,
        SharedData.getInstance(this).getSingleConnections()
      );
    this.listView.setAdapter(this.listadpt);

  }

  /*
   * Override to make a more efficient case.
   */
  @Override
  public void clickHome(View view) {
    closeDrawer();
  }

  /**
   * This function's responsible of what happens when the Pair btn is pressed.
   * @param view -
   */
  public void clickPair(View view) {
    redirectActv(this, Add.class);
  }

  /**
   * This function's responsible of what happens when a device widget is pressed.
   * @param view -
   */
  public void clickControl(View view) {
    redirectActv(this, Control.class);
  }
}
