package com.horizon.OrionConnection;

import android.view.HapticFeedbackConstants;
import android.view.View;

import com.horizon.networking.NetRunnableFactory;
import com.horizon.networking.Executioner.Actions;
import com.horizon.utils.Vars;

/**
 * This class defines the the function that's responsible of controlling the pc
 * through the net.
 */
public class OrionControlBaseActivity extends BaseOrionActivity {

    /**
     * A function that's responsible of passing the action to the desired connection
     * / connections
     * 
     * @param action - The action to pass.
     */
    protected void control(Actions action, View view) {
        view.setHapticFeedbackEnabled(true);
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY,
                HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);
        if (Vars.isFromGroup) {
            Vars.newGroup.send(action);
        } else {
            NetRunnableFactory.passAnAction(Vars.connection.getName(), action);
        }
    }
}
