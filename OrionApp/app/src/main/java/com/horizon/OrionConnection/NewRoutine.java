package com.horizon.OrionConnection;

import android.os.Bundle;
import android.view.View;

public class NewRoutine extends BaseOrionActivity {


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_new_routine);
    this.menu = findViewById(R.id.drawer);
  }

  public void clickConfirm(View view) {
    redirectActv(this, Routines.class);
  }

  public void clickAddAct(View view) { ;
  }

}
