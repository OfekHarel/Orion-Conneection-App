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

  /*
   * What happens when return / back btn is pressed
   */
  @Override
  public void onBackPressed() {
    redirectActv(this, Control.class);
  }

  /**
   * This function's responsible of what happens when the off power btn is
   * pressed.
   * 
   * @param view -
   */
  public void clickPowerOff(View view) {
    control(Actions.OFF, view);
  }

  public void clickPowerOn(View view) {
    control(Actions.OFF, view);
  }

  /**
   * This function's responsible of what happens when the restart btn is pressed.
   * 
   * @param view -
   */
  public void clickRestart(View view) {
    control(Actions.RESTART, view);
  }

  /**
   * This function's responsible of what happens when the lock btn is pressed.
   * 
   * @param view -
   */
  public void clickLock(View view) {
    control(Actions.LOCK, view);
  }

  /**
   * This function's responsible of what happens when the sleep btn is pressed.
   * 
   * @param view -
   */
  public void clickSleep(View view) {
    control(Actions.SLEEP, view);
  }
}
