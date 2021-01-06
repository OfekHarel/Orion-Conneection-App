package com.horizon.OrionConnection;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputLayout;
import com.horizon.utils.Data;
import com.horizon.utils.Vars;
import com.horizon.utils.conn.GroupConnection;

import java.util.Objects;

public class PairGroup extends BaseOrionActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private TextInputLayout name;
    private TextView errorDisp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pair_group);
        this.menu = findViewById(R.id.drawer);

        this.listView = findViewById(R.id.new_connection_group_list);
        this.adapter = new ArrayAdapter<String>
                (this, R.layout.list_row, Vars.newGroup.get());
        this.listView.setAdapter(adapter);

        this.name = findViewById(R.id.new_group_pair_name);

        this.errorDisp = findViewById(R.id.text_error_add_group);
    }

    private boolean validateName() {
        String nameInput = Objects
                .requireNonNull(name.getEditText())
                .getText()
                .toString()
                .trim();

        if (nameInput.isEmpty()) {
            name.setError(getResources().getString(R.string.empty_error));
            return false;
        } else if ((Data.getInstance().getConnectionName(nameInput) != null) ||
                (Vars.newGroup.isExist(nameInput) ||
                        Data.getInstance().getGroupConnectionName(nameInput) != null)) {
            name.setError(
                    "This name " + getResources().getString(R.string.taken_error)
            );
            return false;

        } else {
            name.setError(null);
            return true;
        }
    }

    private boolean validateConnections() {
        if(Vars.newGroup.getList().isEmpty() || Vars.newGroup.getList().size() < 2) {
            this.errorDisp.setText("Must have at list 2 devices");
            return false;
        } else {
            this.errorDisp.setText("");
            return true;
        }
    }

    public void clickAdd(View view) {
        redirectActv(this, Pair.class);
    }

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