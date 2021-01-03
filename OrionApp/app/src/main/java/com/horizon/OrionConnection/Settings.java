package com.horizon.OrionConnection;


import android.os.Bundle;
import android.view.View;

public class Settings extends BaseOrionActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        this.menu = findViewById(R.id.drawer);
    }

    @Override
    public void clickSettings(View view) {
        closeDrawer();
    }
}