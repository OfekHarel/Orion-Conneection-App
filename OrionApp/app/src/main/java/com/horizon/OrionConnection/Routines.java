package com.horizon.OrionConnection;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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

  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if(event.getAction() == KeyEvent.ACTION_DOWN)
    {
      if (keyCode == KeyEvent.KEYCODE_BACK) {//ENTER WAS PRESSED!
        redirectActv(this, MainActivity.class);
        return true;
      }
    }
    return super.onKeyDown(keyCode, event);
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

  /**
   * This function's responsible of what happens when the edit btn is pressed.
   * @param view -
   */
  public void clickEditRoutine(View view) {

    if (SharedData.getInstance(this).getRoutines().isEmpty()) {
      Toast.makeText(this, "No routines have been setup yet", Toast.LENGTH_SHORT).
              show();
      return;
    } else {
      redirectActv(this, EditRoutines.class);
    }
  }
}
