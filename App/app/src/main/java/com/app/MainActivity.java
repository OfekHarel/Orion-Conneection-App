package com.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity implements BaseActive {

    private ImageButton controlBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    @Override
    public void activitiesSwitcher(Object a) {
        if (a instanceof BaseActivities) {
            Intent intent = null;
            switch ((Activities) a) {
                case CONTROL:
                    intent = new Intent(this, ControlActivity.class);
                    break;
                case INFO:
                    intent = new Intent(this, InfoActivity.class);
                    break;
                case PC_INFO:
                    intent = new Intent(this, PcInfoActivity.class);
                    break;
                case ROUTINES:
                    intent = new Intent(this, RoutinesActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }

    enum Activities implements BaseActivities{
        COMPUTER, ADD
    }
}