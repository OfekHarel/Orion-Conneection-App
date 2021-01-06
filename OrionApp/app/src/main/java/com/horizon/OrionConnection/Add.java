package com.horizon.OrionConnection;

import android.os.Bundle;
import android.view.View;

import com.horizon.utils.Vars;

public class Add extends BaseOrionActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        this.menu = findViewById(R.id.drawer);
    }

    public void clickSingleDev(View view) {
        redirectActv(this, Pair.class);
        Vars.isFromGroup = false;
    }

    public void clickGroupDev(View view) {
        redirectActv(this, PairGroup.class);
        Vars.isFromGroup = true;
        Vars.newGroup.clear();
    }
}