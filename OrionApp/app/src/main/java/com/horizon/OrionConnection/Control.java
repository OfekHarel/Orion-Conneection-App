package com.horizon.OrionConnection;

import android.os.Bundle;
import android.view.View;

import com.horizon.networking.Executioner.Actions;
import com.horizon.networking.NetRunnable;

public class Control extends BaseOrionActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_control);

    this.menu = findViewById(R.id.drawer);
  }

  /**
   * This function's responsible of what happens when the main power btn is pressed.
   * @param view -
   */
  public void clickPowerToggle(View view) {}

  /**
   * This function's responsible of what happens when the previous btn is pressed.
   * @param view -
   */
  public void clickPrev(View view) {
    NetRunnable.msgact = Actions.PREV;
  }

  /**
   * This function's responsible of what happens when the next btn is pressed.
   * @param view -
   */
  public void clickNext(View view) {
    NetRunnable.msgact = Actions.SKIP;
  }

  /**
   * This function's responsible of what happens when the volume down btn is pressed.
   * @param view -
   */
  public void clickVolDown(View view) {
    NetRunnable.msgact = Actions.VOL_DOWN;
  }

  /**
   * This function's responsible of what happens when the volume up btn is pressed.
   * @param view -
   */
  public void clickVolUp(View view) {
    NetRunnable.msgact = Actions.VOL_UP;
  }

  /**
   * This function's responsible of what happens when the mute btn is pressed.
   * @param view -
   */
  public void clickMute(View view) {
    NetRunnable.msgact = Actions.MUTE;
  }

  /**
   * This function's responsible of what happens when the power options btn is pressed.
   * @param view -
   */
  public void clickPowerOpt(View view) {
    redirectActv(this, PowerOptions.class);
  }

  /**
   * This function's responsible of what happens when the pc info btn is pressed.
   * @param view -
   */
  public void clickPcInfo(View view) {
    redirectActv(this, PCInfo.class);
  }

  /**
   * This function's responsible of what happens when the ppt btn is pressed.
   * @param view -
   */
  public void clickPausePlayToggle(View view) {
    NetRunnable.msgact = Actions.PAUSE_PLAY_TOGGLE;
  }
}
