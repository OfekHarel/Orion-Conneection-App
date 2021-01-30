package com.horizon.OrionConnection;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.horizon.utils.Vars;
import com.horizon.utils.conn.SingleConnection;

public class PCInfo extends BaseOrionActivity {

  private TextView title;
  private TextView os;
  private TextView ram;
  private TextView gpu;
  private TextView cpu;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_pc_info);

    this.menu = findViewById(R.id.drawer);

    this.title = findViewById(R.id.pc_info_title);
    this.os = findViewById(R.id.os_ver);
    this.ram = findViewById(R.id.ram);
    this.gpu = findViewById(R.id.gpu);
    this.cpu = findViewById(R.id.cpu);

    write();
  }

  @SuppressLint("SetTextI18n")
  private void write() {
    SingleConnection con = Vars.connection;
    if (con != null) {
      this.title.setText(con.getName() + "'s Info");
      this.os.setText(con.getInfo()[0]);
      this.ram.setText(con.getInfo()[1] + " GB");
      this.cpu.setText(con.getInfo()[2]);
      this.gpu.setText(con.getInfo()[3]);
    }
  }
}
