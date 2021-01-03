package com.horizon.OrionConnection;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class Pair extends BaseOrionActivity {
    private TextInputLayout id;
    private TextInputLayout password;
    private String idInfo;
    private String passwordInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pair);

        menu = findViewById(R.id.drawer);
        this.id = findViewById(R.id.dev_id);
        this.password = findViewById(R.id.dev_password);
    }

    private boolean validateID() {
        String idInput = Objects.requireNonNull(id.getEditText()).getText().toString().trim();

        if (idInput.isEmpty()) {
            id.setError("Field cannot be empty!");
            return false;
        } else if (idInput.length() > getResources().getInteger(R.integer.id_length)) {
            id.setError("ID too long");
            return false;
        } else if(idInput.length() < getResources().getInteger(R.integer.id_length)) {
            id.setError("ID too short");
            return false;
        }

        else {
            id.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = Objects.requireNonNull(password.getEditText()).getText().toString().trim();

        if (passwordInput.isEmpty()) {
            password.setError("Field cannot be empty!");
            return false;
        } else if (passwordInput.length() > getResources().getInteger(R.integer.password_length)) {
            password.setError("Password too long");
            return false;
        } else if(passwordInput.length() < getResources().getInteger(R.integer.password_length)) {
            password.setError("Password too short");
            return false;
        }

        else {
            password.setError(null);
            return true;
        }
    }
    public void clickConfirm(View view) {
        if (!validateID() | !validatePassword()) {
            return;
        }
        else {
            this.idInfo = Objects.requireNonNull(id.getEditText()).getText().toString();
            this.passwordInfo = Objects.requireNonNull(password.getEditText()).getText().toString();
            String text = String.format("ID: %S \nPassword: %S", this.idInfo, this.passwordInfo);
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        }
    }
}