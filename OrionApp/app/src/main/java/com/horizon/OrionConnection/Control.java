package com.horizon.OrionConnection;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.horizon.networking.Executioner.Actions;
import com.horizon.utils.Vars;

public class Control extends OrionControlBaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_control);

    this.menu = findViewById(R.id.drawer);
  }

  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if(event.getAction() == KeyEvent.ACTION_DOWN)
    {
      if (keyCode == KeyEvent.KEYCODE_BACK) {
        if(Vars.isFromGroup) {
          redirectActv(this, Groups.class);
        } else {
          redirectActv(this, MainActivity.class);
        }
        return true;
      }
    }

    return super.onKeyDown(keyCode, event);
  }


  /**
   * This function's responsible of what happens when the previous btn is pressed.
   * @param view -
   */
  public void clickPrev(View view) {
    control(Actions.PREV, view);
  }

  /**
   * This function's responsible of what happens when the next btn is pressed.
   * @param view -
   */
  public void clickNext(View view) {
    control(Actions.SKIP, view);
  }

  /**
   * This function's responsible of what happens when the volume down btn is pressed.
   * @param view -
   */
  public void clickVolDown(View view) {
    control(Actions.VOL_DOWN, view);
  }

  /**
   * This function's responsible of what happens when the volume up btn is pressed.
   * @param view -
   */
  public void clickVolUp(View view) {
    control(Actions.VOL_UP, view);
  }

  /**
   * This function's responsible of what happens when the mute btn is pressed.
   * @param view -
   */
  public void clickMute(View view) {
    control(Actions.MUTE, view);
  }

  /**
   * This function's responsible of what happens when the ppt btn is pressed.
   * @param view -
   */
  public void clickPausePlayToggle(View view) {
    control(Actions.PAUSE_PLAY_TOGGLE, view);
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
