package com.horizon.OrionConnection;

import android.os.Build;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.RequiresApi;

import com.horizon.utils.SharedData;
import com.horizon.utils.conn.GroupConnection;

import java.util.ArrayList;

public class EditGroups extends BaseOrionActivity {

    private ListView listView; // List view of the singles
    private ArrayAdapter<String> adapter;
    private ArrayList<GroupConnection> chosen = new ArrayList<GroupConnection>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_groups);

        this.menu = findViewById(R.id.drawer);

        /*
         * List view init.
         */
        this.listView = findViewById(R.id.edit_groups);
        this.adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice,
                SharedData.getInstance(this).getGroupsAsStringArr());
        this.listView.setAdapter(adapter);
        this.listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        EditGroups instance = this;

        /*
         * This code is responsible of what happens when a single widget is pressed.
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = (String) parent.getItemAtPosition(position);
                if (SharedData.getInstance(instance).getGroupConnectionByName(name, chosen) == null) {
                    chosen.add(SharedData.getInstance(instance).getGroupConnectionByName(name));
                } else {
                    chosen.remove(SharedData.getInstance(instance).getGroupConnectionByName(name, chosen));
                }
            }
        });
    }

    /*
     * What happens when return / back btn is pressed
     */
    @Override
    public void onBackPressed() {
        redirectActv(this, Groups.class);
    }

    /**
     * This function's responsible of what happens when the delete btn is pressed
     * 
     * @param view -
     */
    @RequiresApi(api = Build.VERSION_CODES.R)
    public void clickDelete(View view) {
        view.setHapticFeedbackEnabled(true);
        view.performHapticFeedback(HapticFeedbackConstants.CONFIRM);
        SharedData.getInstance(this).cleanGroups(this.chosen);
        redirectActv(this, Groups.class);
    }
}