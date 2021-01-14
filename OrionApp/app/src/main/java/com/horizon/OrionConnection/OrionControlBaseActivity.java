package com.horizon.OrionConnection;

import com.horizon.networking.Executioner.Actions;
import com.horizon.utils.Vars;

public class OrionControlBaseActivity extends BaseOrionActivity{
    protected void control (Actions action) {
        if (Vars.isFromGroup) {
            Vars.newGroup.send(action);
        } else {
            Vars.connection.getRunnable().setAction(action);
        }
    }
}
