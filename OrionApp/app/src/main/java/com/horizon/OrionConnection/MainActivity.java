package com.horizon.OrionConnection;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.horizon.networking.Executioner;
import com.horizon.utils.Data;
import com.horizon.utils.conn.ConnectionListAdapter;

public class MainActivity extends BaseOrionActivity {

  private ListView listView;
  private ConnectionListAdapter listadpt;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    this.menu = findViewById(R.id.drawer);

    this.listView = findViewById(R.id.main_list);
    this.listadpt =
      new ConnectionListAdapter(
        this,
        R.layout.single_conn,
        Data.getInstance().getConnections()
      );
    this.listView.setAdapter(this.listadpt);
  }

  @Override
  public void clickHome(View view) {
    closeDrawer();
  }

  public void clickPair(View view) {
    redirectActv(this, Add.class);
  }

  public void clickControl(View view) {
    redirectActv(this, Control.class);
  }
}
