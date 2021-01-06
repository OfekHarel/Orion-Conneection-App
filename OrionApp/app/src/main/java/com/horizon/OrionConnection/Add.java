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

    /**
     * This function's responsible of what happens when single device option btn is pressed.
     * @param view -
     */
    public void clickSingleDev(View view) {
        redirectActv(this, Pair.class);
        Vars.isFromGroup = false;
    }

    /**
     * This function's responsible of what happens when the group device option btn is pressed.
     * @param view -
     */
    public void clickGroupDev(View view) {
        redirectActv(this, PairGroup.class);
        Vars.isFromGroup = true;
        Vars.newGroup.clear();
    }
}
