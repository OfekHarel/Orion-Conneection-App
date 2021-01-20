package com.horizon.OrionConnection;

import com.horizon.networking.Executioner.Actions;
import com.horizon.utils.Vars;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class PowerOptions extends OrionControlBaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_power_options);

    this.menu = findViewById(R.id.drawer);
  }

  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if(event.getAction() == KeyEvent.ACTION_DOWN)
    {
      if (keyCode == KeyEvent.KEYCODE_BACK) {//ENTER WAS PRESSED!
        redirectActv(this, Control.class);
        return true;
      }
    }
    return super.onKeyDown(keyCode, event);
  }

  /**
   * This function's responsible of what happens when the restart btn is pressed.
   * @param view -
   */
  public void clickRestart(View view) {
    control(Actions.RESTART, view);
  }

  /**
   * This function's responsible of what happens when the lock btn is pressed.
   * @param view -
   */
  public void clickLock(View view) {
    control(Actions.LOCK, view);
  }

  /**
   * This function's responsible of what happens when the sleep btn is pressed.
   * @param view -
   */
  public void clickSleep(View view) {
    control(Actions.SLEEP, view);
  }
}
