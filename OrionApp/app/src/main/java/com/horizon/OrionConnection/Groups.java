package com.horizon.OrionConnection;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.horizon.utils.Data;
import com.horizon.utils.Vars;

import java.util.ArrayList;

public class Groups extends BaseOrionActivity {

  private ListView listView;
  private ArrayAdapter<String> adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_groups);

    this.menu = findViewById(R.id.drawer);

    this.listView = findViewById(R.id.group_list);
    this.adapter = new ArrayAdapter<>
            (this, R.layout.list_row,
                    Data.getInstance().getGroupsAsStringArr());
    this.listView.setAdapter(adapter);

    Groups instance = this;
    this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long id) {
        redirectActv(instance, Control.class);
      }
    });
  }

  @Override
  public void clickGroups(View view) {
    closeDrawer();
  }

  public void clickGroup(View view) {
    redirectActv(this, Control.class);
  }
}
