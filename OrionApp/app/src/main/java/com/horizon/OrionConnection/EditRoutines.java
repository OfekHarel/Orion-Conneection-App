package com.horizon.OrionConnection;


import android.os.Build;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.RequiresApi;

import com.horizon.utils.SharedData;
import com.horizon.utils.routine.Routine;

import java.util.ArrayList;

public class EditRoutines extends BaseOrionActivity {

    private ListView listView; // List view of the singles
    private ArrayAdapter<String> adapter;
    private ArrayList<Routine> choosen = new ArrayList<Routine>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_routines);

        this.menu = findViewById(R.id.drawer);

        /*
         * List view init.
         */
        this.listView = findViewById(R.id.edit_routines);
        this.adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice,
                SharedData.getInstance(this).getRoutinesAsArrayString());
        this.listView.setAdapter(adapter);
        this.listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        EditRoutines instance = this;

        /*
         * This code is responsible of what happens when a single widget is pressed.
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = (String) parent.getItemAtPosition(position);
                if(SharedData.getInstance(instance).getRoutineConnectionByName(name, choosen) == null) {
                    choosen.add(SharedData.getInstance(instance).getRoutineConnectionByName(name));
                } else {
                    choosen.remove(SharedData.getInstance(instance).getRoutineConnectionByName(name, choosen));
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN)
        {
            if (keyCode == KeyEvent.KEYCODE_BACK) {//ENTER WAS PRESSED!
                redirectActv(this, Routines.class);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    /**
    * This function's responsible of what happens when the delete btn is pressed
    * @param view -
    */
    @RequiresApi(api = Build.VERSION_CODES.R)
    public void clickDelete(View view) {
        view.setHapticFeedbackEnabled(true);
        view.performHapticFeedback(HapticFeedbackConstants.CONFIRM);
        SharedData.getInstance(this).cleanRoutines(this.choosen);
        redirectActv(this, Routines.class);
    }
}