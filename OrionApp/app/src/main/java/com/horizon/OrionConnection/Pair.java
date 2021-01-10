package com.horizon.OrionConnection;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputLayout;
import com.horizon.networking.NetCommRunnable;
import com.horizon.utils.SharedData;
import com.horizon.utils.Vars;
import com.horizon.utils.conn.SingleConnection;
import java.util.Objects;

public class Pair extends BaseOrionActivity {
  /*
   * Widgets init
   */
  private TextInputLayout id;
  private TextInputLayout password;
  private TextInputLayout name;

  /*
   *  Info vars init
   */
  private String idInfo;
  private String passwordInfo;
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
    this.password = findViewById(R.id.dev_password);
    this.name = findViewById(R.id.pc_name);
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
   * This function will check whether the Password is valid.
   * <p>The Conditions are:
   * <p>- Not empty
   * <p>- Longer / shorter then the matching defined length
   * @return true or false according to the current conditions.
   */
  private boolean validatePassword() {
    String passwordInput = Objects.requireNonNull(
            password.getEditText()).getText().toString().trim(); // raw data

    if (passwordInput.isEmpty()) { // empty
      password.setError(getResources().getString(R.string.empty_error));
      return false;

    } else if (passwordInput.length() >
            getResources().getInteger(R.integer.password_length)) { // longer...
      password.setError(getResources().getString(R.string.long_error));
      return false;

    } else if (passwordInput.length() <
            getResources().getInteger(R.integer.password_length)) { // shorter...
      password.setError(getResources().getString(R.string.short_error));
      return false;

    } else { // valid
      password.setError(null);
      return true;
    }
  }

  /**
   * This function's responsible of what happens when the logo on the menu is pressed.
   * @param view -
   */
  public void clickConfirm(View view) {
    if (!validateID() | !validatePassword() | !validateName()) { // checking if valid
      return;

    } else {
      this.idInfo = Objects.requireNonNull(id.getEditText()).getText().toString();
      this.passwordInfo = Objects.requireNonNull(password.getEditText()).getText().toString();
      this.nameInfo = Objects.requireNonNull(name.getEditText()).getText().toString();

      String text = String.format("ID: %s \nPassword: %s\nName: %s",
              this.idInfo, this.passwordInfo, this.nameInfo);

      SingleConnection connection = new SingleConnection(this.nameInfo, this.idInfo);

      /*
       * Taking care of 2 scenarios that the use paired from single device or from a group devices.
       */
      if (Vars.isFromGroup) {
        redirectActv(this, PairGroup.class);
        Vars.newGroup.add(connection);

      } else {
        NetCommRunnable.pair(this.idInfo, this.nameInfo);
        MainActivity.t.start();

//        if (!SharedData.getInstance(this).getIsPaired()) {
//          this.id.setError("Cannot Connect to this ID");
//          MainActivity.t.interrupt();
//          return;
//        }

        redirectActv(this, MainActivity.class);
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        SharedData.getInstance(this).addSingleConnection(connection);
      }
    }
  }
}
