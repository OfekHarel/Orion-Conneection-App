package com.horizon.OrionConnection;

import android.os.Bundle;
import android.view.View;

import com.horizon.utils.SharedData;

public class Settings extends BaseOrionActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_settings);

    this.menu = findViewById(R.id.drawer);
  }

  /*
   * Override to make a more efficient case.
   */
  @Override
  public void clickSettings(View view) {
    closeDrawer();
  }

  /**
   * This function's responsible of what happens when the delete connection btn is pressed.
   * @param view -
   */
  public void clickDeleteCon(View view) {
    SharedData.getInstance(this).cleanSingle();
  }

  /**
   * This function's responsible of what happens when the delete groups btn is pressed.
   * @param view -
   */
  public void clickDeleteGroups(View view) {
    SharedData.getInstance(this).cleanGroups();
  }
}
