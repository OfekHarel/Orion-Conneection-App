package com.horizon.OrionConnection;

import com.horizon.utils.SharedData;
import com.horizon.utils.Vars;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
    this.adapter = new ArrayAdapter<>(this, R.layout.list_row, SharedData.getInstance(this).getGroupsAsStringArr());
    this.listView.setAdapter(adapter);

    /*
     * This code is responsible of what happens when a group widget is pressed.
     */
    this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long id) {
        Vars.isFromGroup = true;
        Vars.newGroup = SharedData.getInstance(Groups.this).getGroupConnectionByName(adapter.getItem(arg2));
        redirectActv(Groups.this, Control.class);
      }
    });
  }

  /*
   * What happens when return / back btn is pressed
   */
  @Override
  public void onBackPressed() {
    redirectActv(this, MainActivity.class);
  }

  /*
   * Override to make a more efficient case.
   */
  @Override
  public void clickGroups(View view) {
    closeDrawer();
  }

  /**
   * This function's responsible of what happens when the add btn is pressed
   * 
   * @param view -
   */
  public void clickAddGroup(View view) {
    if (SharedData.getInstance(this).getSingleConnections().isEmpty()) {
      setPopWin(this, "Warning", "No devices have been added yet", "Add device",
          (dialog, which) -> redirectActv(Groups.this, Add.class)).show();

    } else {
      redirectActv(this, AddGroup.class);
    }
  }

  /**
   * This function's responsible of what happens when the edit btn is pressed
   * 
   * @param view -
   */
  public void clickEditGroups(View view) {
    if (SharedData.getInstance(this).getGroupConnections().isEmpty()) {
      setPopWin(this, "Warning", "No groups have been added yet", "Add group", (dialog, which) -> {
        if (SharedData.getInstance(this).getSingleConnections().isEmpty()) {
          redirectActv(Groups.this, Add.class);
        } else {
          redirectActv(Groups.this, AddGroup.class);
        }
      }).show();
    } else {
      redirectActv(this, EditGroups.class);
    }
  }
}
