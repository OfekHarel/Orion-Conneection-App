package com.horizon.OrionConnection;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.horizon.utils.Vars;

public class About extends BaseOrionActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_about);

    this.menu = findViewById(R.id.drawer);
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
  public void clickAbout(View view) {
    closeDrawer();
  }
}
