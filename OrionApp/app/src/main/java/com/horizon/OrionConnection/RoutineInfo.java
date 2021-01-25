package com.horizon.OrionConnection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.horizon.utils.Vars;

public class RoutineInfo extends BaseOrionActivity {

    private TextView name;
    private TextView time;
    private TextView dev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_info);

        this.menu = findViewById(R.id.drawer);

        this.name = findViewById(R.id.routine_info_name);
        this.name.setText(Vars.routine.getName());

        this.dev = findViewById(R.id.routine_info_dev);
        this.dev.setText(Vars.routine.getDevName());

        this.time = findViewById(R.id.routine_info_time);
        this.time.setText(Vars.routine.getTime().toString());
    }

    /*
     * What happens when return / back btn is pressed
     */
    @Override
    public void onBackPressed() {
        redirectActv(this, Groups.class);
    }
}