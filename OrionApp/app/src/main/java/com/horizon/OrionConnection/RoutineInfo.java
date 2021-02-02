package com.horizon.OrionConnection;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.horizon.utils.SharedData;
import com.horizon.utils.Vars;
import com.horizon.utils.routine.Routine;

import java.util.ArrayList;

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

    /**
     * This function's responsible of deleting the selected connections when dlt btn is pressed
     * @param view
     */
    public void clickDelete(View view) {
        ArrayList<Routine> arr = new ArrayList<>();
        arr.add(Vars.routine);
        SharedData.getInstance(this).cleanRoutines(arr);
        Vars.routine = null;
        redirectActv(this, Groups.class);
    }
}