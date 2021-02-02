package com.horizon.OrionConnection;

import android.os.Build;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.RequiresApi;

import com.horizon.utils.SharedData;
import com.horizon.utils.conn.GroupConnection;

import java.util.ArrayList;

public class EditGroups extends BaseOrionActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private final ArrayList<GroupConnection> chosen = new ArrayList<>();

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

        /*
         * This code is responsible of what happens when a single widget is pressed.
         */
        listView.setOnItemClickListener((parent, view, position, id) -> {
            choose(position, false);
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
        setPopWin(this, "Warning", "Note - it will delete any collisions", "Delete anyway",
                (dialog, which) ->{
                    SharedData.getInstance(EditGroups.this).cleanGroups(this.chosen);
                    redirectActv(EditGroups.this, Groups.class);
                    preformVibration(view, HapticFeedbackConstants.CONFIRM);
                }).show();
    }

    /**
     * This function's responsible of what happens when the check all btn is pressed
     * @param view -
     */
    public void clickCheckAllGroups(View view) {
        boolean toggleTrue = SharedData.getInstance(this).getGroupConnections().size() != chosen.size();
        for (int i=0; i < listView.getAdapter().getCount(); i++) {
            listView.setItemChecked(i, toggleTrue);
            choose(i, toggleTrue);
        }
    }

    /**
     * the logic interaction of list checker
     * @param index - the position of the item
     * @param all - is adding all
     */
    private void choose(int index, boolean all) {
        String name = (String) listView.getItemAtPosition(index);
        if (SharedData.getInstance(EditGroups.this).getGroupConnectionByName(name, chosen) == null) {
            chosen.add(SharedData.getInstance(EditGroups.this).getGroupConnectionByName(name));
        } else if (!all){
            chosen.remove(SharedData.getInstance(EditGroups.this).getGroupConnectionByName(name, chosen));
        }
    }
}
