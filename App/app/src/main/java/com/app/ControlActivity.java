package com.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.app.activitytools.BaseActive;

public class ControlActivity extends AppCompatActivity implements BaseActive {

    private ImageButton backBtn;
    private  ImageButton powerOptionsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        backBtn = findViewById(R.id.control_back_btn);
        backBtn.setOnClickListener(v -> activitiesSwitcher(Activities.BACK));

        powerOptionsBtn = findViewById(R.id.other_power_options);
        powerOptionsBtn.setOnClickListener(v -> activitiesSwitcher(Activities.OTHER_POWR_OPTION));

    }

    @Override
    public void activitiesSwitcher(Object a) {
        if (a instanceof BaseActivities) {
            Intent intent = null;
            switch ((Activities) a) {
                case BACK:
                    intent = new Intent(this, MainActivity.class);
                    break;
                case OTHER_POWR_OPTION:
                    intent = new Intent(this, OtherPowerOptionsActivity.class);
                    break;

            }
            startActivity(intent);
        }
    }

    enum Activities implements BaseActivities {
        BACK,
        OTHER_POWR_OPTION;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}