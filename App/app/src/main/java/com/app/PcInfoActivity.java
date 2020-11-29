package com.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.app.activitytools.BaseActive;

public class PcInfoActivity extends AppCompatActivity implements BaseActive {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pc_info);
        setTitle(R.string.pc_info_panel);
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

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}