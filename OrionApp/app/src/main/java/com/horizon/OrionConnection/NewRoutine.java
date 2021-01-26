package com.horizon.OrionConnection;

import android.os.Build;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.RequiresApi;

import com.google.android.material.textfield.TextInputLayout;
import com.horizon.networking.Executioner.Actions;
import com.horizon.networking.NetRunnableFactory;
import com.horizon.networking.NetworkPackets;
import com.horizon.utils.SharedData;
import com.horizon.utils.Vars;
import com.horizon.utils.conn.GroupConnection;
import com.horizon.utils.conn.SingleConnection;
import com.horizon.utils.routine.Routine;
import com.horizon.utils.routine.Time;

import java.util.Objects;

public class NewRoutine extends BaseOrionActivity {

  private TextInputLayout name;
  private TextInputLayout time;
  private Spinner actSpinner;
  private Spinner devSpinner;

  private ArrayAdapter<String> devSpinnerAdapter;

  private String[] timeFormatted;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_new_routine);
    this.menu = findViewById(R.id.drawer);

    this.name = findViewById(R.id.routine_name);
    this.time = findViewById(R.id.new_routine_time);
    this.actSpinner = findViewById(R.id.act_spinner);
    this.devSpinner = findViewById(R.id.device_spinner);

    this.devSpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
        SharedData.getInstance(this).getAllConnectionAsString());
    this.devSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    this.devSpinner.setAdapter(this.devSpinnerAdapter);
    this.devSpinnerAdapter.notifyDataSetChanged();
  }

  /*
   * What happens when return / back btn is pressed
   */
  @Override
  public void onBackPressed() {
    redirectActv(this, Routines.class);
  }

  /**
   * This function will check whether the Name is valid.
   * <p>
   * The Conditions are:
   * <p>
   * - Not empty
   * <p>
   * - Not already used
   * 
   * @return true or false according to the current conditions.
   */
  private boolean validateName() {
    String nameInput = Objects.requireNonNull(name.getEditText()).getText().toString().trim(); // raw data

    if (nameInput.isEmpty()) { // empty
      name.setError(getResources().getString(R.string.empty_error));
      return false;

    } else if ((SharedData.getInstance(this).getSingleConnectionByName(nameInput) != null)
        || (Vars.isFromGroup && Vars.newGroup.isExist(nameInput))) { // not being used
      name.setError("This name " + getResources().getString(R.string.taken_error));
      return false;

    } else { // valid
      name.setError(null);
      return true;
    }
  }

  /**
   * This function will check whether the Name is valid.
   * <p>
   * The Conditions are:
   * <p>
   * - Not empty
   * <p>
   * - Not formatted
   * 
   * @return true or false according to the current conditions.
   */
  private boolean validateTime() {
    String timeInput = Objects.requireNonNull(time.getEditText()).getText().toString().trim(); // raw data

    String[] timeFormatted = timeInput.split(":");
    if (timeInput.isEmpty() || timeInput.equals("hh:mm") || timeFormatted.length < 2) { // empty
      time.setError(getResources().getString(R.string.empty_error));
      return false;

    } else if (Integer.parseInt(timeFormatted[0]) >= 24 || Integer.parseInt(timeFormatted[1]) >= 60) { // not formatted
      time.setError("Not in the right time format");
      return false;

    } else { // valid
      time.setError(null);
      this.timeFormatted = timeFormatted;
      return true;
    }
  }

  /**
   * This function's responsible of what happens when the confirm btn is pressed.
   * 
   * @param view -
   */
  @RequiresApi(api = Build.VERSION_CODES.R)
  public void clickConfirm(View view) {
    view.setHapticFeedbackEnabled(true);

    if (!validateName() | !validateTime()) {
      view.performHapticFeedback(HapticFeedbackConstants.REJECT);
      return;
    } else {
      view.performHapticFeedback(HapticFeedbackConstants.CONFIRM);
      String d_name = this.devSpinner.getSelectedItem().toString();
      Routine routine;
      String r_name = Objects.requireNonNull(this.name.getEditText()).getText().toString();

      GroupConnection group = SharedData.getInstance(this).getGroupConnectionByName(d_name);
      SingleConnection single = SharedData.getInstance(this).getSingleConnectionByName(d_name);

      String msg;

      if (group != null) {
        routine = new Routine(this.actSpinner.getSelectedItem().toString(), r_name, new Time(this.timeFormatted),
            group);

        msg = NetworkPackets.assamble("ROUT", routine.getTime().toString(), Time.getTimeZoneParam(),
            Actions.getByFullName(routine.getActions()).getAsString(), routine.getName());

        Actions.ROUTINE.setStr(msg);

        group.routine();

      } else {
        routine = new Routine(this.actSpinner.getSelectedItem().toString(), r_name, new Time(this.timeFormatted),
            single);

        msg = NetworkPackets.assamble("ROUT", routine.getTime().toString(), Time.getTimeZoneParam(),
            Actions.getByFullName(routine.getActions()).getAsString(), routine.getName());

        Actions.ROUTINE.setStr(msg);

        NetRunnableFactory.passAnAction(single.getName(), Actions.ROUTINE);
      }

      SharedData.getInstance(this).addRoutines(routine);
      redirectActv(this, Routines.class);
    }
  }
}
