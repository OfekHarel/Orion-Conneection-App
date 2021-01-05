package com.horizon.OrionConnection;

import android.os.Bundle;
import android.view.View;

public class PowerOptions extends BaseOrionActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_options);

        this.menu = findViewById(R.id.drawer);
    }

    public void clickRestart(View view) {
    }

    public void clickLock(View view) {
    }
}