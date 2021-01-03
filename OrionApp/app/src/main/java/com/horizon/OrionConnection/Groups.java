package com.horizon.OrionConnection;

import android.os.Bundle;
import android.view.View;

public class Groups extends BaseOrionActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
        this.menu = findViewById(R.id.drawer);
    }

    @Override
    public void clickGroups(View view) {
        closeDrawer();
    }
}