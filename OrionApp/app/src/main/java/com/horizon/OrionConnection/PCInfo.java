package com.horizon.OrionConnection;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.horizon.networking.Executioner;
import com.horizon.networking.NetworkPackets;
import com.horizon.utils.Vars;
import com.horizon.utils.conn.SingleConnection;

import java.util.Timer;
import java.util.TimerTask;

public class PCInfo extends BaseOrionActivity {

  private TextView title;
  private TextView os;
  private TextView ram;
  private TextView gpu;
  private TextView cpu;

  private ProgressBar cpuUsageBar;
  private ProgressBar ramUsageBar;
  private TextView cpuUsageText;
  private TextView ramUsageText;

  private final Timer taskTimer = new Timer();
  private TimerTask task;
  private Handler handler;


  @SuppressLint({"StaticFieldLeak", "SetTextI18n"})
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

    ((TextView) findViewById(R.id.cpu_title)).setText("CPU Usage");
    ((TextView) findViewById(R.id.ram_title)).setText("RAM Usage");



    this.cpuUsageBar = findViewById(R.id.cpu_progress_bar);
    this.ramUsageBar = findViewById(R.id.ram_progress_bar);
    this.cpuUsageText = findViewById(R.id.cpu_text_view_progress);
    this.ramUsageText = findViewById(R.id.ram_text_view_progress);

    this.handler = new Handler();

    this.task = new TimerTask() {
      @Override
      public void run() {
        String msg = "";
        String[] split;

        Vars.connection.getRunnable().setAction(Executioner.Actions.USE);

          do {
            msg = Vars.connection.getRunnable().getRecvMsg();
          } while (!NetworkPackets.split(msg)[0].equals(
                  NetworkPackets.IncomingOperations.USAGE.getAsString()));

          split = NetworkPackets.split(msg);
          msg = "";
          update(Integer.parseInt(split[1]), Integer.parseInt(split[2]));
      }

      @SuppressLint("SetTextI18n")
      private void update(int cpu, int ram) {
        handler.post(() -> cpuUsageBar.setProgress(cpu, true));
        handler.post(() -> ramUsageBar.setProgress(ram, true));

        handler.post(() -> cpuUsageText.setText(cpu + "%"));
        handler.post(() -> ramUsageText.setText(ram + "%"));
      }
    };
    runTask();
  }

  /**
   * Writes the pc specs according to the connections {@code string[] PC_Info}
   */
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

  @Override
  protected void onResume() {
    super.onResume();
    runTask();
  }

  @Override
  protected void onPause() {
    super.onPause();
    cancelTask();
  }

  private void runTask() {
    try {
      taskTimer.schedule(task, 0, 1690);
    } catch (IllegalStateException ignored) {

    }
  }

  private void cancelTask() {
    try {
      handler.post(() -> this.taskTimer.cancel());
    } catch (IllegalStateException ignored) {
    }
  }
}
