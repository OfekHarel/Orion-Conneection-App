package com.horizon.OrionConnection;


import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.HapticFeedbackConstants;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.google.android.material.textfield.TextInputLayout;
import com.horizon.utils.SharedData;
import com.horizon.utils.Vars;
import com.horizon.utils.conn.GroupConnection;
import com.horizon.utils.conn.SingleConnection;

import java.util.ArrayList;
import java.util.Objects;

public class AddGroup extends BaseOrionActivity {

    private ListView listView; // List view of the singles
    private ArrayAdapter<String> adapter;
    private ArrayList<SingleConnection> choosen = new ArrayList<SingleConnection>();

    private TextInputLayout name;
    private TextView errorDisp; // for displaying useful errors according to the list.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        this.menu = findViewById(R.id.drawer);

        this.name = findViewById(R.id.new_group_name);
        this.errorDisp = findViewById(R.id.text_error_add_new_group);

        /*
         * List view init.
         */
        this.listView = findViewById(R.id.new_group_connection_list);
        this.adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice,
                SharedData.getInstance(this).getSinglesAsStringArr());
        this.listView.setAdapter(adapter);
        this.listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        AddGroup instance = this;

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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN)
        {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                redirectActv(this, Groups.class);
                return true;
            }
        }

        return super.onKeyDown(keyCode, event);
    }

    /**
     * This function will check whether the Name is valid.
     * <p>The Conditions are:
     * <p>- Not empty
     * <p>- Not already used
     * @return true or false according to the current conditions.
     */
    private boolean validateName() {
        String nameInput = Objects.requireNonNull(
                name.getEditText()).getText().toString().trim(); // raw data

        if (nameInput.isEmpty()) { // empty
            name.setError(getResources().getString(R.string.empty_error));
            return false;

        } else if (SharedData.getInstance(this).getGroupConnectionByName(nameInput) != null) { // not being used
            name.setError("This name " + getResources().getString(R.string.taken_error));
            return false;

        } else { // valid
            name.setError(null);
            return true;
        }
    }

    /**
     * This function checks if the list stands in the min requirements to finish the pair.
     * @return Whether it's valid or not.
     */
    private boolean validateConnections() {
        if(choosen.isEmpty() || choosen.size() < 2) {
            // at list 2 devices.
            this.errorDisp.setText("Must have at list 2 devices");
            return false;

        } else { // valid
            this.errorDisp.setText("");
            return true;
        }
    }

    /**
    * This function's responsible of what happens when the done btn is pressed
    * @param view -
    */
    @RequiresApi(api = Build.VERSION_CODES.R)
    public void clickDone(View view) {
        view.setHapticFeedbackEnabled(true);
        if (!validateName() | !validateConnections()) {
            view.performHapticFeedback(HapticFeedbackConstants.REJECT);
            return;
        } else {
            view.performHapticFeedback(HapticFeedbackConstants.CONFIRM);
            String nameInfo = Objects.requireNonNull(name.getEditText()).getText().toString();
            SharedData.getInstance(this).addGroupConnection(new GroupConnection(nameInfo, choosen),
                    false);
            redirectActv(this, Groups.class);
        }
    }
}