package com.horizon.OrionConnection;

import android.os.Bundle;
import android.view.View;

public class About extends BaseOrionActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_about);

    this.menu = findViewById(R.id.drawer);
  }

  /*
   * Override to make a more efficient case.
   */
  @Override
  public void clickAbout(View view) {
    closeDrawer();
  }
}
