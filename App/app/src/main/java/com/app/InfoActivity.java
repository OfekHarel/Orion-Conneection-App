package com.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.app.activitytools.BaseActive;

public class InfoActivity extends AppCompatActivity implements BaseActive {

    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        backBtn = findViewById(R.id.info_back_btn);
        backBtn.setOnClickListener(v -> activitiesSwitcher(Activities.BACK));
    }

    @Override
    public void activitiesSwitcher(Object a) {
        if (a instanceof BaseActivities) {
            Intent intent = null;
            switch ((Activities) a) {
                case BACK:
                    intent = new Intent(this, MainActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }

    enum Activities implements BaseActivities {
        BACK;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}