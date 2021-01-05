package com.horizon.OrionConnection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class BaseOrionActivity extends AppCompatActivity {

  protected DrawerLayout menu;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void clickMenu(View view) {
    openDrawer();
  }

  private void openDrawer() {
    this.menu.openDrawer(GravityCompat.START);
  }

  public void clickLogo(View view) {
    closeDrawer();
  }

  public void closeDrawer() {
    if (this.menu.isDrawerOpen(GravityCompat.START)) {
      this.menu.closeDrawer(GravityCompat.START);
    }
  }

  public void clickHome(View view) {
    redirectActv(this, MainActivity.class);
  }

  public void clickGroups(View view) {
    redirectActv(this, Groups.class);
  }

  public void clickRoutines(View view) {
    redirectActv(this, Routines.class);
  }

  public void clickSettings(View view) {
    redirectActv(this, Settings.class);
  }

  public void clickAbout(View view) {
    redirectActv(this, About.class);
  }

  protected void redirectActv(AppCompatActivity activity, Class aClass) {
    Intent intent = new Intent(activity, aClass);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    activity.startActivity(intent);
  }

  @Override
  protected void onPause() {
    super.onPause();
    closeDrawer();
  }
}
