package com.horizon.OrionConnection;

import android.os.Bundle;
import android.view.View;

public class Routines extends BaseOrionActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_routines);

    this.menu = findViewById(R.id.drawer);
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
  public void clickAdd(View view) {
    redirectActv(this, NewRoutine.class);
  }
}
