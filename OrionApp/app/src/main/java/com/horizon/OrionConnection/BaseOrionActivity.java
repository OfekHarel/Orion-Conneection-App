package com.horizon.OrionConnection;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

/**
 * This class inherits from the AppCompatActivity and adds other layer of useful
 * tool and repeated functions and vars across most of the GUI activities.
 * <p>
 * It contains the background implementation of the menu drawer.
 */
public class BaseOrionActivity extends AppCompatActivity {

  /**
   * The drawer layout that represent the menu preset. Need to be set in the
   * onCreate() function.
   */
  protected DrawerLayout menu;

  protected ProgressBar loadingBar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  /**
   * This function's responsible of what happens when the menu btn is pressed.
   * 
   * @param view -
   */
  public void clickMenu(View view) {
    openDrawer();
  }

  /**
   * This function gives the ability to open the drawer.
   */
  private void openDrawer() {
    this.menu.openDrawer(GravityCompat.START);
  }

  /**
   * This function's responsible of what happens when the logo on the menu is
   * pressed.
   * 
   * @param view -
   */
  public void clickLogo(View view) {
    closeDrawer();
  }

  /**
   * This function gives the ability to close the drawer (if it's open).
   */
  public void closeDrawer() {
    if (this.menu.isDrawerOpen(GravityCompat.START)) {
      this.menu.closeDrawer(GravityCompat.START);
    }
  }

  /**
   * This function's responsible of what happens when the Home btn on the menu is
   * pressed.
   * <p>
   * Need's to be overridden in the Home activities class.
   * 
   * @param view -
   */
  public void clickHome(View view) {
    redirectActv(this, MainActivity.class);
  }

  /**
   * This function's responsible of what happens when the Groups btn on the menu
   * is pressed.
   * <p>
   * Need's to be overridden in the Groups activities class.
   * 
   * @param view -
   */
  public void clickGroups(View view) {
    redirectActv(this, Groups.class);
  }

  /**
   * This function's responsible of what happens when the Routines btn on the menu
   * is pressed.
   * <p>
   * Need's to be overridden in the Routines activities class.
   * 
   * @param view -
   */
  public void clickRoutines(View view) {
    redirectActv(this, Routines.class);
  }

  /**
   * This function's responsible of what happens when the Settings btn on the menu
   * is pressed.
   * <p>
   * Need's to be overridden in the Settings activities class.
   * 
   * @param view -
   */
  public void clickSettings(View view) {
    redirectActv(this, Settings.class);
  }

  /**
   * This function's responsible of what happens when the About btn on the menu is
   * pressed.
   * <p>
   * Need's to be overridden in the About activities class
   * 
   * @param view -
   */
  public void clickAbout(View view) {
    redirectActv(this, About.class);
  }

  /**
   * This function allows a simple transition between activities.
   * 
   * @param activity The current activity (this)
   * @param aClass   The dest activity .class
   */
  protected void redirectActv(AppCompatActivity activity, Class aClass) {
    Intent intent = new Intent(activity, aClass);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    activity.startActivity(intent);
  }

  /*
   * When Exiting the app - the drawer menu will be closed.
   */
  @Override
  protected void onPause() {
    super.onPause();
    closeDrawer();
  }

  /**
   * changes the loading bar state<p>
   * NEED TO EXIST IN THE XML FILE.
   * @param mode - the mode to change
   * @param handler - the handler to use
   */
  protected void changeLoadingBarState(int mode, Handler handler) {
    handler.post(() -> runOnUiThread(() -> loadingBar.setVisibility(mode)));
  }

  /**
   * builds a pop up dialog window.
   * sets a default cancel btn.
   * @param context - the context where it will show up.
   * @param title - the title of the window.
   * @param msg - the pop up window description
   * @param positiveBtn - positive option string
   * @param positiveListener - what will happen when pressing positive.
   * @return the popup win object
   */
  protected AlertDialog.Builder setPopWin(Context context, String title, String msg, String positiveBtn,
      DialogInterface.OnClickListener positiveListener) {
    AlertDialog.Builder alert = new AlertDialog.Builder(context);
    alert.setCancelable(true);
    alert.setTitle(title);
    alert.setMessage(msg);
    alert.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
    alert.setPositiveButton(positiveBtn, positiveListener);
    return alert;
  }

  /**
   * Exits the app to the android main screen (home page)
   */
  protected void exit() {
    Intent intent = new Intent(Intent.ACTION_MAIN);
    intent.addCategory(Intent.CATEGORY_HOME);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
  }

  /**
   * preforms a vibration
   * @param view - the context
   * @param type - which vibration type
   */
  protected void preformVibration(View view, int type) {
    view.setHapticFeedbackEnabled(true);
    view.performHapticFeedback(type);
  }
}
