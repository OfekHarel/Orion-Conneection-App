package com.horizon.OrionConnection;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.horizon.utils.Vars;

public class About extends BaseOrionActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_about);

    this.menu = findViewById(R.id.drawer);
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
  public void clickAbout(View view) {
    closeDrawer();
  }
}
