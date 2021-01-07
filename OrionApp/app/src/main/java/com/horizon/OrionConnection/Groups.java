package com.horizon.OrionConnection;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.horizon.utils.Data;

public class Groups extends BaseOrionActivity {

  private ListView listView; // List view of the groups
  private ArrayAdapter<String> adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_groups);

    this.menu = findViewById(R.id.drawer);

    /*
     * List view init.
     */
    this.listView = findViewById(R.id.group_list);
    this.adapter = new ArrayAdapter<>
            (this, R.layout.list_row,
                    Data.getInstance().getGroupsAsStringArr());
    this.listView.setAdapter(adapter);

    /*
     * This code is responsible of what happens when a group widget is pressed.
     */
    Groups instance = this;
    this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long id) {
        redirectActv(instance, Control.class);
      }
    });
  }

  /*
   * Override to make a more efficient case.
   */
  @Override
  public void clickGroups(View view) {
    closeDrawer();
  }
}
