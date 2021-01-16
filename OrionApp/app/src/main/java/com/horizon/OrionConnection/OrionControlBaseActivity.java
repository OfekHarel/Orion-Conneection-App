package com.horizon.OrionConnection;

import com.horizon.networking.NetRunnableFactory;
import com.horizon.networking.Executioner.Actions;
import com.horizon.utils.SharedData;
import com.horizon.utils.Vars;

/**
 * This class defines the the function that's responible
 *  of controling the pc through the net.
 */
public class OrionControlBaseActivity extends BaseOrionActivity {

    /**
     * A function that's responible of passing the action to the desired 
     * connection / connections
     * @param action - The action to pass.
     */
    protected void control (Actions action) {
        if (Vars.isFromGroup) {
            Vars.newGroup.send(action);
        } else {
            NetRunnableFactory.passAnAction(Vars.connection.getName(), action);
        }
    }
}
