package com.horizon.OrionConnection;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import com.google.android.material.textfield.TextInputLayout;
import com.horizon.utils.SharedData;
import com.horizon.utils.Vars;
import com.horizon.utils.conn.SingleConnection;

import java.util.Objects;

import android.view.HapticFeedbackConstants;

import androidx.annotation.RequiresApi;

public class Pair extends BaseOrionActivity {
  /*
   * Widgets init
   */
  private TextInputLayout id;
  private TextInputLayout name;

  /*
   * Info vars init
   */
  private String idInfo;
  private String nameInfo;

  private Handler handler;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_pair);

    handler = new Handler();
    this.menu = findViewById(R.id.drawer);
    this.loadingBar = findViewById(R.id.loader);
    changeLoadingBarState(View.INVISIBLE, handler);

    /*
     * Widgets init
     */
    this.id = findViewById(R.id.dev_id);
    this.name = findViewById(R.id.pc_name);
  }

  /*
   * What happens when return / back btn is pressed
   */
  @Override
  public void onBackPressed() {
    redirectActv(this, Add.class);
  }

  /**
   * This function will check whether the ID is valid.
   * <p>
   * The Conditions are:
   * <p>
   * - Not empty
   * <p>
   * - Longer / shorter then the matching defined length
   * <p>
   * - Not already used
   * 
   * @return true or false according to the current conditions.
   */
  private boolean validateID() {
    String idInput = Objects.requireNonNull(id.getEditText()).getText().toString().trim(); // raw data

    if (idInput.isEmpty()) { // empty.
      id.setError(getResources().getString(R.string.empty_error));
      return false;

    } else if (idInput.length() > getResources().getInteger(R.integer.id_length)) { // longer then...
      id.setError(getResources().getString(R.string.long_error));
      return false;

    } else if (idInput.length() < getResources().getInteger(R.integer.id_length)) {// shorter then...
      id.setError(getResources().getString(R.string.short_error));
      return false;

    } else if (SharedData.getInstance(this).getSingleConnectionByID(idInput) != null) { // not being used
      id.setError("This ID " + getResources().getString(R.string.taken_error));
      return false;

    } else { // valid
      id.setError(null);
      return true;
    }
  }

  /**
   * This function will check whether the Name is valid.
   * <p>
   * The Conditions are:
   * <p>
   * - Not empty
   * <p>
   * - Not already used
   * 
   * @return true or false according to the current conditions.
   */
  private boolean validateName() {
    String nameInput = Objects.requireNonNull(name.getEditText()).getText().toString().trim(); // raw data

    if (nameInput.isEmpty()) { // empty
      name.setError(getResources().getString(R.string.empty_error));
      return false;

    } else if ((SharedData.getInstance(this).getSingleConnectionByName(nameInput) != null)
        || (Vars.isFromGroup && Vars.newGroup.isExist(nameInput))) { // not being used
      name.setError("This name " + getResources().getString(R.string.taken_error));
      return false;

    } else { // valid
      name.setError(null);
      return true;
    }
  }

  /**
   * This function's responsible of what happens when the confirm btn is pressed
   * 
   * @param view -
   */
  @RequiresApi(api = Build.VERSION_CODES.R)
  public void clickConfirm(View view) {

    view.setHapticFeedbackEnabled(true);
    Pair instance = this;

    if (!validateID() | !validateName()) { // checking if valid
      preformVibration(view, HapticFeedbackConstants.REJECT);
      return;

    } else {
      this.idInfo = Objects.requireNonNull(id.getEditText()).getText().toString();
      this.nameInfo = Objects.requireNonNull(name.getEditText()).getText().toString();

      boolean isMaster = this.nameInfo.equals(SharedData.getInstance(this).getAdminCards()[0])
              && this.idInfo.equals(SharedData.getInstance(this).getAdminCards()[1]);

      SingleConnection connection = new SingleConnection(this.nameInfo, this.idInfo);

      changeLoadingBarState(View.VISIBLE, handler);
      @SuppressLint("StaticFieldLeak")
      AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
        @Override
        protected void onPreExecute() {
          super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
          if (!isMaster && !Vars.DEBUG) {
            if (!connection.initConnection()) {
              changeLoadingBarState(View.INVISIBLE, handler);
              preformVibration(view, HapticFeedbackConstants.REJECT);
              handler.post(() -> id.setError("Invalid ID - check for typos"));
            } else {
              handler.post(() -> id.setError(null));

              /*
               * Taking care of 2 scenarios that the use paired from single device or from a
               * group devices.
               */
              if (Vars.isFromGroup) {
                Vars.newGroup.add(connection);
                redirectActv(instance, PairGroup.class);

              } else {
                changeLoadingBarState(View.INVISIBLE, handler);
                preformVibration(view, HapticFeedbackConstants.CONFIRM);
                SharedData.getInstance(instance).addSingleConnection(connection);

                handler.post(connection::flowConnection);
                handler.post(() -> redirectActv(instance, MainActivity.class));
              }
            }
          } else {
            preformVibration(view, HapticFeedbackConstants.CONFIRM);
            SharedData.getInstance(instance).addSingleConnection(connection);
            redirectActv(instance, MainActivity.class);
          }
          return null;
        }
      };
      task.execute();
    }
  }
}
