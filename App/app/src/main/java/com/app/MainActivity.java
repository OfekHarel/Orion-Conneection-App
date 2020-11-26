package com.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.app.activity_tools.BaseActive;

public class MainActivity extends AppCompatActivity implements BaseActive {

    private ImageButton controlBtn;
    private ImageButton infoBtn;
    private ImageButton pcInfoBtn;
    private ImageButton routinesBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controlBtn = findViewById(R.id.control_btn);
        controlBtn.setOnClickListener(v -> activitiesSwitcher(Activities.CONTROL));

        infoBtn = findViewById(R.id.info_btn);
        infoBtn.setOnClickListener(v -> activitiesSwitcher(Activities.INFO));

        pcInfoBtn = findViewById(R.id.pc_info_btn);
        pcInfoBtn.setOnClickListener(v -> activitiesSwitcher(Activities.PC_INFO));

        routinesBtn = findViewById(R.id.routines_btn);
        routinesBtn.setOnClickListener(v -> activitiesSwitcher(Activities.ROUTINES));
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
        CONTROL,
        INFO,
        PC_INFO,
        ROUTINES;
    }
}