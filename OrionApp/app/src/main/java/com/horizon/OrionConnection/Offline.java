package com.horizon.OrionConnection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

public class Offline extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline);
    }

    private boolean isNetavilable() {
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

//    private boolean isServerOnline() {
//        Thread t = new Thread(new NetRunnable());
//        t.start();
//        boolean is = NetRunnable.isOnline;
//        t.interrupt();
//        return is;
//    }
//
//    public boolean isOffline() {
//        return  !isNetavilable() || !isServerOnline();
//    }
}