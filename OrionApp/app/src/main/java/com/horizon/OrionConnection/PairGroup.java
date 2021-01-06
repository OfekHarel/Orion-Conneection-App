package com.horizon.OrionConnection;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.textfield.TextInputLayout;
import com.horizon.utils.Data;
import com.horizon.utils.Vars;
import com.horizon.utils.conn.GroupConnection;

public class PairGroup extends BaseOrionActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private TextInputLayout name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pair_group);
        this.menu = findViewById(R.id.drawer);

        this.listView = findViewById(R.id.new_connection_group_list);
        this.adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, Vars.newGroup.get());
        this.listView.setAdapter(adapter);

        this.name = findViewById(R.id.new_group_pair_name);
    }

    public void clickAdd(View view) {
        redirectActv(this, Pair.class);
    }

    public void clickDone(View view) {
        redirectActv(this, MainActivity.class);
        GroupConnection groupConnection =
                new GroupConnection(this.name.getEditText().getText().toString(),
                        Vars.newGroup.getList());
        Vars.newGroup.clear();
        Data.getInstance().addConnection(groupConnection);

    }
}