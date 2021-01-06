package com.horizon.OrionConnection;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.horizon.utils.Data;
import com.horizon.utils.Vars;
import com.horizon.utils.conn.GroupConnection;

import java.util.Objects;

public class PairGroup extends BaseOrionActivity {

    private ListView listView; // added devices groups
    private ArrayAdapter<String> adapter;

    private TextInputLayout name; // group name

    private TextView errorDisp; // for displaying useful errors according to the list.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pair_group);

        this.menu = findViewById(R.id.drawer);

        /*
         * List view init.
         */
        this.listView = findViewById(R.id.new_connection_group_list);
        this.adapter = new ArrayAdapter<String>
                (this, R.layout.list_row, Vars.newGroup.get());
        this.listView.setAdapter(adapter);

        /*
         * Widget init.
         */
        this.name = findViewById(R.id.new_group_pair_name);
        this.errorDisp = findViewById(R.id.text_error_add_group);
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

        } else if ((Data.getInstance().getConnectionName(nameInput) != null) ||
                (Vars.newGroup.isExist(nameInput) ||
                        Data.getInstance().getGroupConnectionName(nameInput) != null)) {
            // already exists considering the current devices added and the former once
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
        if(Vars.newGroup.getList().isEmpty() || Vars.newGroup.getList().size() < 2) {
            // at list 2 devices.
            this.errorDisp.setText("Must have at list 2 devices");
            return false;

        } else { // valid
            this.errorDisp.setText("");
            return true;
        }
    }

    /**
     * This function's responsible of what happens when the add btn is pressed.
     * @param view -
     */
    public void clickAdd(View view) {
        redirectActv(this, Pair.class);
    }

    /**
     * This function's responsible of what happens when the done btn is pressed.
     * @param view -
     */
    public void clickDone(View view) {
        if (!validateName() | !validateConnections()) {
            return;
        }

        redirectActv(this, MainActivity.class);
        GroupConnection groupConnection =
                new GroupConnection(this.name.getEditText().getText().toString(),
                        Vars.newGroup.getList());
        Data.getInstance().addConnections(groupConnection);
        Data.getInstance().addConnection(groupConnection);
        Vars.newGroup.clear();
    }
}
