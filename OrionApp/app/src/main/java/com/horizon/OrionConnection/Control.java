package com.horizon.OrionConnection;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.horizon.networking.Executioner.Actions;
import com.horizon.utils.SharedData;
import com.horizon.utils.Vars;

public class Control extends OrionControlBaseActivity {

  private ImageView magicBTN;
  private TextView textView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_control);

    this.menu = findViewById(R.id.drawer);

    this.magicBTN = findViewById(R.id.magic_btn);
    if (SharedData.getInstance(this).isMagic()) {
      this.magicBTN.setVisibility(View.VISIBLE);
    } else {
      this.magicBTN.setVisibility(View.INVISIBLE);
    }

    this.textView = findViewById(R.id.control_title);
    String txt = Vars.isFromGroup? Vars.newGroup.getName(): Vars.connection.getName();
    this.textView.setText(txt);
  }

  /*
   * What happens when return / back btn is pressed
   */
  @Override
  public void onBackPressed() {
    if (Vars.isFromGroup) {
      redirectActv(this, Groups.class);
    } else {
      redirectActv(this, MainActivity.class);
    }
  }

  /**
   * This function's responsible of what happens when the previous btn is pressed.
   * 
   * @param view -
   */
  public void clickPrev(View view) {
    control(Actions.PREV, view);
  }

  /**
   * This function's responsible of what happens when the next btn is pressed.
   * 
   * @param view -
   */
  public void clickNext(View view) {
    control(Actions.SKIP, view);
  }

  /**
   * This function's responsible of what happens when the volume down btn is
   * pressed.
   * 
   * @param view -
   */
  public void clickVolDown(View view) {
    control(Actions.VOL_DOWN, view);
  }

  /**
   * This function's responsible of what happens when the volume up btn is
   * pressed.
   * 
   * @param view -
   */
  public void clickVolUp(View view) {
    control(Actions.VOL_UP, view);
  }

  /**
   * This function's responsible of what happens when the mute btn is pressed.
   * 
   * @param view -
   */
  public void clickMute(View view) {
    control(Actions.MUTE, view);
  }

  /**
   * This function's responsible of what happens when the ppt btn is pressed.
   * 
   * @param view -
   */
  public void clickPausePlayToggle(View view) {
    control(Actions.PAUSE_PLAY_TOGGLE, view);
  }

  /**
   * This function's responsible of what happens when the power options btn is
   * pressed.
   * 
   * @param view -
   */
  public void clickPowerOpt(View view) {
    redirectActv(this, PowerOptions.class);
  }

  /**
   * This function's responsible of what happens when the pc info btn is pressed.
   * 
   * @param view -
   */
  public void clickControlInfo(View view) {
    if (Vars.isFromGroup) {
      redirectActv(this, GroupInfo.class);
    } else {
      redirectActv(this, PCInfo.class);
    }
  }

  /**
   * This function's responsible of what happens when the pc magic btn is pressed.
   * @param view -
   */
  public void clickMagic(View view) {
    if (SharedData.getInstance(this).isMagic()) {
      control(Actions.MAGIC, view);
    } else {
      Toast.makeText(this, "Your not ready for that yet.", Toast.LENGTH_SHORT).show();
    }
  }
}
