package com.horizon.OrionConnection;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputLayout;
import com.horizon.utils.Data;
import com.horizon.utils.conn.SingleConnection;
import java.util.Objects;

public class Pair extends BaseOrionActivity {

  private TextInputLayout id;
  private TextInputLayout password;
  private TextInputLayout name;
  private String idInfo;
  private String passwordInfo;
  private String nameInfo;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_pair);

    menu = findViewById(R.id.drawer);
    this.id = findViewById(R.id.dev_id);
    this.password = findViewById(R.id.dev_password);
    this.name = findViewById(R.id.pc_name);
  }

  private boolean validateID() {
    String idInput = Objects
      .requireNonNull(id.getEditText())
      .getText()
      .toString()
      .trim();

    if (idInput.isEmpty()) {
      id.setError(getResources().getString(R.string.empty_error));
      return false;
    } else if (
      idInput.length() > getResources().getInteger(R.integer.id_length)
    ) {
      id.setError(getResources().getString(R.string.long_error));
      return false;
    } else if (
      idInput.length() < getResources().getInteger(R.integer.id_length)
    ) {
      id.setError(getResources().getString(R.string.short_error));
      return false;
    } else if (Data.getInstance().getConnectionByID(idInput) != null) {
      id.setError("This ID " + getResources().getString(R.string.taken_error));
      return false;
    } else {
      id.setError(null);
      return true;
    }
  }

  private boolean validateName() {
    String nameInput = Objects
      .requireNonNull(name.getEditText())
      .getText()
      .toString()
      .trim();

    if (nameInput.isEmpty()) {
      name.setError(getResources().getString(R.string.empty_error));
      return false;
    } else if (Data.getInstance().getConnectionName(nameInput) != null) {
      name.setError(
        "This name " + getResources().getString(R.string.taken_error)
      );
      return false;
    } else {
      name.setError(null);
      return true;
    }
  }

  private boolean validatePassword() {
    String passwordInput = Objects
      .requireNonNull(password.getEditText())
      .getText()
      .toString()
      .trim();

    if (passwordInput.isEmpty()) {
      password.setError(getResources().getString(R.string.empty_error));
      return false;
    } else if (
      passwordInput.length() >
      getResources().getInteger(R.integer.password_length)
    ) {
      password.setError(getResources().getString(R.string.long_error));
      return false;
    } else if (
      passwordInput.length() <
      getResources().getInteger(R.integer.password_length)
    ) {
      password.setError(getResources().getString(R.string.short_error));
      return false;
    } else {
      password.setError(null);
      return true;
    }
  }

  public void clickConfirm(View view) {
    if (!validateID() | !validatePassword() | !validateName()) {
      return;
    } else {
      this.idInfo =
        Objects.requireNonNull(id.getEditText()).getText().toString();
      this.passwordInfo =
        Objects.requireNonNull(password.getEditText()).getText().toString();
      this.nameInfo =
        Objects.requireNonNull(name.getEditText()).getText().toString();
      String text = String.format(
        "ID: %s \nPassword: %s\nName: %s",
        this.idInfo,
        this.passwordInfo,
        this.nameInfo
      );
      Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
      Data
        .getInstance()
        .addConnection(new SingleConnection(this.nameInfo, this.idInfo));
      redirectActv(this, MainActivity.class);
    }
  }
}
