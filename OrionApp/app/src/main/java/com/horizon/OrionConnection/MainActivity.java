package com.horizon.OrionConnection;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.horizon.utils.SharedData;
import com.horizon.utils.Vars;
import com.horizon.utils.conn.ConnectionListAdapter;

import fr.castorflex.android.circularprogressbar.CircularProgressDrawable;

public class MainActivity extends BaseOrionActivity {

  private ListView listView; // List view of the device list.
  private ConnectionListAdapter listadpt;

  private int backBTNCounter = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    this.menu = findViewById(R.id.drawer);
    this.loadingBar = findViewById(R.id.loader);
    changeLoadingBarState(View.INVISIBLE, new Handler());

   MainActivity instance = this;


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

    listView.setOnItemClickListener((arg0, arg1, arg2, id) -> {
      Vars.connection = listadpt.getItem(arg2);
      Vars.isFromGroup = false;
      redirectActv(instance, Control.class);
    });
    loadingBar.setVisibility(View.INVISIBLE);
  }

    @Override
    public void onBackPressed() {
      backBTNCounter++;
      if (backBTNCounter == 1) {
          Toast.makeText(this, "Alart! Hit back button again to Exit",
                  Toast.LENGTH_SHORT).show();
      } else if (backBTNCounter == 2){
          backBTNCounter = 0;
          Intent intent = new Intent(Intent.ACTION_MAIN);
          intent.addCategory(Intent.CATEGORY_HOME);
          intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
          startActivity(intent);
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
      if (SharedData.getInstance(this).getSingleConnections().isEmpty()) {
        Toast.makeText(this, "No devices have been synced yet", Toast.LENGTH_SHORT).
                show();
        return;
      } else {
        redirectActv(this, EditMainConnection.class);
      }
  }
}
