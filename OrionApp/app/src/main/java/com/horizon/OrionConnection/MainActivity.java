package com.horizon.OrionConnection;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.horizon.utils.SharedData;
import com.horizon.utils.Vars;
import com.horizon.utils.conn.ConnectionListAdapter;

public class MainActivity extends BaseOrionActivity {

  private ListView listView; // List view of the device list.
  private ConnectionListAdapter listAdpt;

  private int backBTNCounter = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    this.menu = findViewById(R.id.drawer);
    this.loadingBar = findViewById(R.id.loader);
    changeLoadingBarState(View.INVISIBLE, new Handler());

    /*
     * List view init.
     */
    this.listView = findViewById(R.id.main_list);
    this.listAdpt = new ConnectionListAdapter(this, R.layout.single_conn,
        SharedData.getInstance(this).getSingleConnections());
    this.listView.setAdapter(this.listAdpt);

    /*
     * This code is responsible of what happens when a connection widget is pressed.
     */
    listView.setOnItemClickListener((arg0, arg1, arg2, id) -> {
      Vars.connection = listAdpt.getItem(arg2);
      Vars.isFromGroup = false;
      redirectActv(MainActivity.this, Control.class);
    });
  }

  /*
   * What happens when return / back btn is pressed
   */
  @Override
  public void onBackPressed() {
    backBTNCounter++;
    if (backBTNCounter == 1) {
      Toast.makeText(this, "Alert! Hit back button again to Exit", Toast.LENGTH_SHORT).show();
    } else if (backBTNCounter == 2) {
      backBTNCounter = 0;
      exit();
    }
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
   * 
   * @param view -
   */
  public void clickPair(View view) {
    redirectActv(this, Add.class);
  }

  /**
   * This function's responsible of what happens when the edit btn is pressed
   * 
   * @param view -
   */
  public void clickEdit(View view) {
    if (SharedData.getInstance(this).getSingleConnections().isEmpty()) {
      setPopWin(this, "Warning", "No devices have been added yet", "Add device",
          (dialog, which) -> redirectActv(MainActivity.this, Add.class)).show();
    } else {
      redirectActv(this, EditMainConnection.class);
    }
  }
}
