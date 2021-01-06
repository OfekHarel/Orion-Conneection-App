package com.horizon.OrionConnection;

import android.os.Bundle;
import android.view.View;
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
  }

  /*
   * Override to make a more efficient case.
   */
  @Override
  public void clickGroups(View view) {
    closeDrawer();
  }

  /**
   * This function's responsible of what happens when a group widget is pressed.
   * @param view -
   */
  public void clickGroup(View view) {
    redirectActv(this, Control.class);
  }
}
