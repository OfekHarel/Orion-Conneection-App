package com.horizon.networking;

import com.horizon.utils.conn.SingleConnection;

public class OfflineCheck extends NetCommRunnable {

    private final SingleConnection virtualChecker;

    public OfflineCheck() {
        this.virtualChecker = new SingleConnection("0001", "VirtualChecker");
    }

    public boolean check() {
        boolean isOffline = !this.virtualChecker.initConnection();
        if (!isOffline) {
            this.virtualChecker.flowConnection();
            NetRunnableFactory.passAnAction(this.virtualChecker.getName(),
                    Executioner.Actions.DISCONNECT);
        }
        return isOffline;
    }
}
