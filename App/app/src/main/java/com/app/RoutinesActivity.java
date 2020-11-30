package com.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class RoutinesActivity extends AppCompatActivity implements BaseActive {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routines);
        setTitle(R.string.routines_panel);
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