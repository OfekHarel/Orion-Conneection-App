package com.horizon.OrionConnection;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.horizon.utils.SharedData;

public class Routines extends BaseOrionActivity {

  private ListView listView;
  private ArrayAdapter<String> adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_routines);

    this.menu = findViewById(R.id.drawer);

    this.listView = findViewById(R.id.routine_list);
    this.adapter = new ArrayAdapter<>(this, R.layout.list_row,
            SharedData.getInstance(this).getRoutinesAsArrayString());
    this.listView.setAdapter(adapter);
  }

  /*
   * Override to make a more efficient case.
   */
  @Override
  public void clickRoutines(View view) {
    closeDrawer();
  }

  /**
   * This function's responsible of what happens when the add btn is pressed.
   * @param view -
   */
  public void clickAddRoutine(View view) {
    redirectActv(this, NewRoutine.class);
  }

  public void clickEditRoutine(View view) {
    redirectActv(this, EditRoutines.class);
  }
}
