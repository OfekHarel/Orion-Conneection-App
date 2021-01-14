package com.horizon.OrionConnection;

import com.horizon.networking.NetRunnableFactory;
import com.horizon.networking.Executioner.Actions;
import com.horizon.utils.SharedData;
import com.horizon.utils.Vars;

public class OrionControlBaseActivity extends BaseOrionActivity{
    protected void control (Actions action) {
        if (Vars.isFromGroup) {
            Vars.newGroup.send(action);
        } else {
//            NetRunnableFactory.get(Vars.connection.getName()).setAction(action);
            NetRunnableFactory.passAnAction(Vars.connection.getName(), action);

        }
    }
}
