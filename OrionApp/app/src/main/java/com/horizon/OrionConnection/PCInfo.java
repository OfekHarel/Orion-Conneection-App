package com.horizon.OrionConnection;

import android.os.Bundle;

public class PCInfo extends BaseOrionActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_pc_info);

    this.menu = findViewById(R.id.drawer);
  }
}
