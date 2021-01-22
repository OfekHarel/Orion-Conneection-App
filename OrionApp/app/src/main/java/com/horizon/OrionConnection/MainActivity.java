package com.horizon.OrionConnection;

import android.os.AsyncTask;
import android.os.Bundle;
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



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    this.menu = findViewById(R.id.drawer);
    this.loadingBar = findViewById(R.id.loader);

    loadingBar.setVisibility(View.VISIBLE);

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

    listView.setOnItemClickListener((arg0, arg1, arg2, id) -> {
      Vars.connection = listadpt.getItem(arg2);
      Vars.isFromGroup = false;
      redirectActv(instance, Control.class);
    });
    loadingBar.setVisibility(View.INVISIBLE);
  }

  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if(event.getAction() == KeyEvent.ACTION_DOWN)
    {
      if (keyCode == KeyEvent.KEYCODE_BACK) {
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
      if (SharedData.getInstance(this).getSingleConnections().isEmpty()) {
        Toast.makeText(this, "No devices have been synced yet", Toast.LENGTH_SHORT).
                show();
        return;
      } else {
        redirectActv(this, EditMainConnection.class);
      }
  }
}
