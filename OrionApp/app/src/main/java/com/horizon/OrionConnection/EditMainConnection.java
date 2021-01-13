package com.horizon.OrionConnection;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.horizon.utils.SharedData;
import com.horizon.utils.conn.SingleConnection;

import java.util.ArrayList;

public class EditMainConnection extends BaseOrionActivity {

    private ListView listView; // List view of the singles
    private ArrayAdapter<String> adapter;
    private ArrayList<SingleConnection> choosen = new ArrayList<SingleConnection>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_main_connection);

        this.menu = findViewById(R.id.drawer);


        /*
         * List view init.
         */
        this.listView = findViewById(R.id.edit_main_conn);
        this.adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice,
                SharedData.getInstance(this).getSinglesAsStringArr());
        this.listView.setAdapter(adapter);
        this.listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        EditMainConnection instance = this;

        /*
         * This code is responsible of what happens when a single widget is pressed.
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = (String) parent.getItemAtPosition(position);
                if(SharedData.getInstance(instance).getSingleConnectionByName(name, choosen) == null) {
                    choosen.add(SharedData.getInstance(instance).getSingleConnectionByName(name));
                } else {
                    choosen.remove(SharedData.getInstance(instance).getSingleConnectionByName(name, choosen));
                }
            }
        });

    }

    public void clickDelete(View view) {
        SharedData.getInstance(this).cleanSingle(this.choosen);
        redirectActv(this, MainActivity.class);
    }
}