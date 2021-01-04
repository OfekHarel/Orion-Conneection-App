package com.horizon.OrionConnection;

import android.os.Bundle;
import android.view.View;

public class Routines extends BaseOrionActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routines);
        this.menu = findViewById(R.id.drawer);
    }

    @Override
    public void clickRoutines(View view) {
        closeDrawer();
    }

    public void clickAdd(View view) {
        redirectActv(this, NewRoutine.class);
    }
}