package com.horizon.OrionConnection;

import android.os.Bundle;
import android.view.View;

import com.horizon.networking.Executioner.Actions;
import com.horizon.utils.Vars;

public class Control extends BaseOrionActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_control);

    this.menu = findViewById(R.id.drawer);
  }

  private void readyAct(Actions action) {
    if (Vars.isFromGroup) {
      for (int i = 0; i < Vars.newGroup.getList().size(); i++) {
        Vars.newGroup.getList().get(i).getRunnable().setAction(action);
      }
    } else {
      Vars.connection.getRunnable().setAction(action);
    }
  }

  /**
   * This function's responsible of what happens when the main power btn is pressed.
   * @param view -
   */
  public void clickPowerToggle(View view) {
    Vars.connection.getRunnable().setAction(Actions.OFF);
  }

  /**
   * This function's responsible of what happens when the previous btn is pressed.
   * @param view -
   */
  public void clickPrev(View view) {
    Vars.connection.getRunnable().setAction(Actions.PREV);
  }

  /**
   * This function's responsible of what happens when the next btn is pressed.
   * @param view -
   */
  public void clickNext(View view) {
    Vars.connection.getRunnable().setAction(Actions.SKIP);
  }

  /**
   * This function's responsible of what happens when the volume down btn is pressed.
   * @param view -
   */
  public void clickVolDown(View view) {
    Vars.connection.getRunnable().setAction(Actions.VOL_DOWN);
  }

  /**
   * This function's responsible of what happens when the volume up btn is pressed.
   * @param view -
   */
  public void clickVolUp(View view) {
    Vars.connection.getRunnable().setAction(Actions.VOL_UP);
  }

  /**
   * This function's responsible of what happens when the mute btn is pressed.
   * @param view -
   */
  public void clickMute(View view) {
    Vars.connection.getRunnable().setAction(Actions.MUTE);
  }

  /**
   * This function's responsible of what happens when the ppt btn is pressed.
   * @param view -
   */
  public void clickPausePlayToggle(View view) {
    Vars.connection.getRunnable().setAction(Actions.PAUSE_PLAY_TOGGLE);
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
}
