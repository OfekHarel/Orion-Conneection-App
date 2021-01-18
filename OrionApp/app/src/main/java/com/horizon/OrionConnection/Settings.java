package com.horizon.OrionConnection;

import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.View;

import com.horizon.utils.SharedData;

public class Settings extends BaseOrionActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_settings);

    this.menu = findViewById(R.id.drawer);
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

  /**
   * This function's responsible of what happens when the delete connection btn is pressed.
   * @param view -
   */
  public void clickDeleteCon(View view) {
    SharedData.getInstance(this).cleanSingle();
    preformVibration(view);
  }

  /**
   * This function's responsible of what happens when the delete groups btn is pressed.
   * @param view -
   */
  public void clickDeleteGroups(View view) {
    SharedData.getInstance(this).cleanGroups();
    preformVibration(view);
  }

  /**
   * This function's responsible of what happens when the delete groups btn is pressed.
   * @param view -
   */
  public void clickDeleteRoutines(View view) {
    SharedData.getInstance(this).cleanRoutines();
    preformVibration(view);
  }
}
