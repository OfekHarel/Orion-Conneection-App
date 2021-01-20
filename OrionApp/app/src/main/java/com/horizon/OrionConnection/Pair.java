package com.horizon.OrionConnection;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputLayout;
import com.horizon.networking.NetCommRunnable;
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
   *  Info vars init
   */
  private String idInfo;
  private String nameInfo;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_pair);

    menu = findViewById(R.id.drawer);

    /*
     * Widgets init
     */
    this.id = findViewById(R.id.dev_id);
    this.name = findViewById(R.id.pc_name);
  }

  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if(event.getAction() == KeyEvent.ACTION_DOWN)
    {
      if (keyCode == KeyEvent.KEYCODE_BACK) {//ENTER WAS PRESSED!
        redirectActv(this, Add.class);
        return true;
      }
    }
    return super.onKeyDown(keyCode, event);
  }

  /**
   * This function will check whether the ID is valid.
   * <p>The Conditions are:
   * <p>- Not empty
   * <p>- Longer / shorter then the matching defined length
   * <p>- Not already used
   * @return true or false according to the current conditions.
   */
  private boolean validateID() {
    String idInput = Objects.requireNonNull(
            id.getEditText()).getText().toString().trim(); // raw data

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
   * <p>The Conditions are:
   * <p>- Not empty
   * <p>- Not already used
   * @return true or false according to the current conditions.
   */
  private boolean validateName() {
    String nameInput = Objects.requireNonNull(
            name.getEditText()).getText().toString().trim(); // raw data

    if (nameInput.isEmpty()) { // empty
      name.setError(getResources().getString(R.string.empty_error));
      return false;

    } else if ((SharedData.getInstance(this).getSingleConnectionByName(nameInput) != null) ||
            (Vars.isFromGroup && Vars.newGroup.isExist(nameInput))) { // not being used
      name.setError("This name " + getResources().getString(R.string.taken_error));
      return false;

    } else { // valid
      name.setError(null);
      return true;
    }
  }


  /**
   * This function's responsible of what happens when the confirm btn is pressed
   * @param view -
   */
  @RequiresApi(api = Build.VERSION_CODES.R)
  public void clickConfirm(View view) {
    view.setHapticFeedbackEnabled(true);
    if (!validateID()  | !validateName()) { // checking if valid
      view.performHapticFeedback(HapticFeedbackConstants.REJECT);
      return;

    } else {
      this.idInfo = Objects.requireNonNull(id.getEditText()).getText().toString();
      this.nameInfo = Objects.requireNonNull(name.getEditText()).getText().toString();

      String text = String.format("ID: %s \nName: %s",
              this.idInfo, this.nameInfo);

      SingleConnection connection = new SingleConnection(this.nameInfo, this.idInfo);

      /*
       * Taking care of 2 scenarios that the use paired from single device or from a group devices.
       */
      if (Vars.isFromGroup) {
        redirectActv(this, PairGroup.class);
        Vars.newGroup.add(connection);

      } else {
        view.performHapticFeedback(HapticFeedbackConstants.CONFIRM);
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        SharedData.getInstance(this).addSingleConnection(connection);
        connection.initConnection();
        redirectActv(this, MainActivity.class);
      }
    }
  }
}
