package com.horizon.OrionConnection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends BaseOrionActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.menu = findViewById(R.id.drawer);
    }

    @Override
    public void clickHome(View view) {
        closeDrawer();
    }

    public void clickAdd(View view) {

    }
}