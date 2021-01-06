package com.horizon.OrionConnection;

import android.os.Bundle;
import android.view.View;

public class NewRoutine extends BaseOrionActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_new_routine);

    this.menu = findViewById(R.id.drawer);
  }

  /**
   * This function's responsible of what happens when the confirm btn is pressed.
   * @param view -
   */
  public void clickConfirm(View view) {
    redirectActv(this, Routines.class);
  }
}
