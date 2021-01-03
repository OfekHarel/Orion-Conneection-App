package com.horizon.OrionConnection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class Control extends BaseOrionActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
        this.menu = findViewById(R.id.drawer);
    }

    public void clickPowerToggle(View view) {
    }

    public void clickPrev(View view) {
    }

    public void clickNext(View view) {
    }

    public void clickVolDown(View view) {
    }

    public void clickVolUp(View view) {
    }

    public void clickPowerOpt(View view) {
        redirectActv(this, PowerOptions.class);
    }

    public void clickPcInfo(View view) {
        redirectActv(this, PCInfo.class);
    }

    public void clickMute(View view) {
    }
}