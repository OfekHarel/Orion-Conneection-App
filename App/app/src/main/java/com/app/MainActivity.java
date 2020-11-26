package com.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity{

    private ImageButton controlBtn;
    private ImageButton infoBtn;
    private ImageButton pcInfoBtn;
    private ImageButton routinesBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controlBtn = (ImageButton) findViewById(R.id.control_btn);
        controlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activitiesSwitcher(Activities.CONTROL);
            }
        });

        infoBtn = (ImageButton) findViewById(R.id.info_btn);
        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activitiesSwitcher(Activities.INFO);
            }
        });

        pcInfoBtn = (ImageButton) findViewById(R.id.pc_info_btn);
        pcInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activitiesSwitcher(Activities.PC_INFO);
            }
        });

        routinesBtn = (ImageButton) findViewById(R.id.routines_btn);
        routinesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activitiesSwitcher(Activities.ROUTINES);
            }
        });
    }

    public void activitiesSwitcher(Activities a) {
        Intent intent = null;
        switch (a){
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

    enum Activities{
        CONTROL,
        INFO,
        PC_INFO,
        ROUTINES;
    }
}