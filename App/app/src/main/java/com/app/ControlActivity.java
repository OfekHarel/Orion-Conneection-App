package com.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class ControlActivity extends AppCompatActivity implements BaseActive {

    private  ImageButton powerOptionsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
        setTitle(R.string.control_panel);

        powerOptionsBtn = findViewById(R.id.other_power_options);
        powerOptionsBtn.setOnClickListener(v -> activitiesSwitcher(Activities.OTHER_POWR_OPTION));
    }

    @Override
    public void activitiesSwitcher(Object a) {
        if (a instanceof BaseActivities) {
            Intent intent = null;
            switch ((Activities) a) {
                case OTHER_POWR_OPTION:
                    intent = new Intent(this, OtherPowerOptionsActivity.class);
                    break;

            }
            startActivity(intent);
        }
    }

    enum Activities implements BaseActivities {
        OTHER_POWR_OPTION, INFO;
    }
}