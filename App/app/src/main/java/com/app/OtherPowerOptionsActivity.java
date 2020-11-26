package com.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.app.activitytools.BaseActive;

public class OtherPowerOptionsActivity extends AppCompatActivity implements BaseActive {

    ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_power_options);

        backBtn = findViewById(R.id.other_power_options_back_btn);
        backBtn.setOnClickListener(v -> activitiesSwitcher(Activities.BACK));
    }

    @Override
    public void activitiesSwitcher(Object a) {
        if (a instanceof BaseActivities) {
            Intent intent = null;
            switch ((Activities) a) {
                case BACK:
                    intent = new Intent(this, ControlActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }

    enum Activities implements BaseActivities {
        BACK;
    }

    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}