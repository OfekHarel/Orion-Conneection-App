package com.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.app.activitytools.BaseActive;

public class OtherPowerOptionsActivity extends AppCompatActivity implements BaseActive {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_power_options);
        setTitle(R.string.powe_opt);
    }

    @Override
    public void activitiesSwitcher(Object a) {
        if (a instanceof BaseActivities) {
            Intent intent = null;
            switch ((Activities) a) {
                case NULL:
                break;
            }
            startActivity(intent);
        }
    }

    enum Activities implements BaseActivities {
        NULL;
    }
}