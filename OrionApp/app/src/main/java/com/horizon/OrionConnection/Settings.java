package com.horizon.OrionConnection;

import android.os.Bundle;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.horizon.utils.SharedData;

public class Settings extends BaseOrionActivity {

  private CheckBox magicCheckBox;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_settings);

    this.menu = findViewById(R.id.drawer);

    this.magicCheckBox = findViewById(R.id.magic_check_box);
    fromMemSettings();
    Log.i("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", Boolean.toString(SharedData.getInstance(Settings.this).isMagic()));
    this.magicCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
      SharedData.getInstance(Settings.this).setMagic(isChecked);
      String txt;
      if (isChecked){
        txt = "So am I.";
      } else {
        txt = "Your problem.";
      }
      Toast.makeText(Settings.this, txt, Toast.LENGTH_SHORT).show();
    });
  }

  private void fromMemSettings() {
    this.magicCheckBox.setChecked(SharedData.getInstance(Settings.this).isMagic());
  }

  /*
   * What happens when return / back btn is pressed
   */
  @Override
  public void onBackPressed() {
    redirectActv(this, MainActivity.class);
  }

  private void preformVibration(View view) {
    view.setHapticFeedbackEnabled(true);
    view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
  }

  /*
   * Override to make a more efficient case.
   */
  @Override
  public void clickSettings(View view) {
    closeDrawer();
    preformVibration(view);
  }

  @Override
  protected void onResume() {
    super.onResume();
    fromMemSettings();
  }
}
