package com.horizon.OrionConnection;

import com.horizon.networking.Executioner.Actions;
import com.horizon.networking.NetCommRunnable;

import android.os.Bundle;
import android.view.View;

public class PowerOptions extends BaseOrionActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_power_options);

    this.menu = findViewById(R.id.drawer);
  }

  /**
   * This function's responsible of what happens when the restart btn is pressed.
   * @param view -
   */
  public void clickRestart(View view) {
    NetCommRunnable.msgact = Actions.RESTART;

  }

  /**
   * This function's responsible of what happens when the lock btn is pressed.
   * @param view -
   */
  public void clickLock(View view) {}

  /**
   * This function's responsible of what happens when the sleep btn is pressed.
   * @param view -
   */
  public void clickSleep(View view) {
  }
}
