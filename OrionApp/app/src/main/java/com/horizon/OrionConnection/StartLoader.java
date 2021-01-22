package com.horizon.OrionConnection;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.horizon.utils.SharedData;

public class StartLoader extends BaseOrionActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_loader);

        this.menu = findViewById(R.id.drawer);
        this.loadingBar = findViewById(R.id.loader);

        StartLoader instance = this;
        Handler handler = new Handler();
        changeLoadingBarState(View.VISIBLE, handler);


        @SuppressLint("StaticFieldLeak")
        AsyncTask<Void, Void, Void> a = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                handler.post(()-> SharedData.getInstance(instance).load(instance));
                handler.post(()-> redirectActv(instance, MainActivity.class));
                return null;
            }
        };
        a.execute();
    }
}