package com.horizon.OrionConnection;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.horizon.utils.SharedData;
import com.horizon.utils.Vars;
import com.horizon.utils.conn.ConnectionListAdapter;

public class MainActivity extends BaseOrionActivity {

  private ListView listView; // List view of the device list.
  private ConnectionListAdapter listadpt;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


    this.menu = findViewById(R.id.drawer);

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

    /*
     * This code is responsible of what happens when a connection widget is pressed.
     */
    MainActivity instance = this;
    this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long id) {
        Vars.connection = listadpt.getItem(arg2);
        Vars.isFromGroup = false;
        redirectActv(instance, Control.class);
      }
    });
  }

  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if(event.getAction() == KeyEvent.ACTION_DOWN)
    {
      if (keyCode == KeyEvent.KEYCODE_BACK) {//ENTER WAS PRESSED!
        return true;
      }
    }
    return super.onKeyDown(keyCode, event);
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
  * This function's responsible of what happens when the edit btn is pressed
  * @param view -
  */
  public void clickEdit(View view) {
      redirectActv(this, EditMainConnection.class);
  }
}
