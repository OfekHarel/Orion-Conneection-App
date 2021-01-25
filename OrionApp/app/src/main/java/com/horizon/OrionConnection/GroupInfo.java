package com.horizon.OrionConnection;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.horizon.utils.Vars;
import com.horizon.utils.conn.ConnectionListAdapter;

public class GroupInfo extends BaseOrionActivity {

    private TextView textView;
    private ListView listView; // List view of the device list.
    private ConnectionListAdapter listadpt;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_info);

        this.menu = findViewById(R.id.drawer);

        this.textView = findViewById(R.id.group_i_title);
        this.textView.setText(Vars.newGroup.getName() + "'s devices:");

        /*
         * List view init.
         */
        this.listView = findViewById(R.id.group_i_list);
        this.listadpt = new ConnectionListAdapter(this, R.layout.single_conn,
                Vars.newGroup.getList());
        this.listView.setAdapter(this.listadpt);
    }

    /*
     * What happens when return / back btn is pressed
     */
    @Override
    public void onBackPressed() {
        redirectActv(this, Control.class);
    }
}