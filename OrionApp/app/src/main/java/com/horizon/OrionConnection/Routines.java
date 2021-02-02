package com.horizon.OrionConnection;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.horizon.utils.SharedData;
import com.horizon.utils.Vars;

public class Routines extends BaseOrionActivity {

  private ListView listView;
  private ArrayAdapter<String> adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_routines);

    this.menu = findViewById(R.id.drawer);

    this.listView = findViewById(R.id.routine_list);
    this.adapter = new ArrayAdapter<>(this, R.layout.list_row, SharedData.getInstance(this).getRoutinesAsArrayString());
    this.listView.setAdapter(adapter);

    /*
     * This code is responsible of what happens when a single widget is pressed.
     */
    listView.setOnItemClickListener((parent, view, position, id) -> {
      String name = (String) parent.getItemAtPosition(position);
      Vars.routine = SharedData.getInstance(Routines.this).getRoutineConnectionByName(name);
      redirectActv(this, RoutineInfo.class);
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
  public void clickRoutines(View view) {
    closeDrawer();
  }

  /**
   * This function's responsible of what happens when the add btn is pressed.
   * 
   * @param view -
   */
  public void clickAddRoutine(View view) {
    if (SharedData.getInstance(this).getSingleConnections().isEmpty()) {
      setPopWin(this, "Warning", "No devices have been added yet", "Add device",
          (dialog, which) -> redirectActv(Routines.this, Add.class)).show();
    } else {
      redirectActv(this, NewRoutine.class);
    }
  }

  /**
   * This function's responsible of what happens when the edit btn is pressed.
   * 
   * @param view -
   */
  public void clickEditRoutine(View view) {

    if (SharedData.getInstance(this).getRoutines().isEmpty()) {
      if (SharedData.getInstance().getSingleConnections().isEmpty()) {
        setPopWin(this, "Warning", "No routines have been added yet", "Add routine",
            (dialog, which) -> redirectActv(Routines.this, Add.class)).show();
      } else {
        setPopWin(this, "Warning", "No routines have been added yet", "Add routine",
            (dialog, which) -> redirectActv(Routines.this, NewRoutine.class)).show();
      }
    } else {
      redirectActv(this, EditRoutines.class);
    }
  }
}
