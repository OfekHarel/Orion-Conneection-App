package com.horizon.OrionConnection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Control extends BaseOrionActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
        this.menu = findViewById(R.id.drawer);
    }
}