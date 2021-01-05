package com.horizon.OrionConnection;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import com.horizon.utils.Data;
import java.util.ArrayList;

public class NewRoutine extends BaseOrionActivity {

  private ListView listView;
  private ArrayList<String> spinners;
  private ArrayAdapter<String> adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_new_routine);
    this.menu = findViewById(R.id.drawer);

    Spinner spinner = (Spinner) findViewById(R.id.device_spinner);
    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
      this,
      android.R.layout.simple_spinner_dropdown_item,
      Data.getInstance().getConnectionsAsStrings()
    );
    spinner.setAdapter(spinnerArrayAdapter);

    String[] spinnerData = getResources()
      .getStringArray(R.array.Spinner_Actions);

    this.adapter =
      new ArrayAdapter<String>(
        this,
        android.R.layout.simple_spinner_item,
        spinnerData
      );
    this.adapter.setDropDownViewResource(
        android.R.layout.simple_spinner_dropdown_item
      );

    this.listView = findViewById(R.id.actions_list);

    listView.setAdapter(new CustomAdapter(this));
  }

  public void clickConfirm(View view) {
    redirectActv(this, Routines.class);
  }

  public void clickAddAct(View view) {
    listView.setAdapter(new CustomAdapter(this));
  }

  private class CustomAdapter extends BaseAdapter {

    LayoutInflater inflater;

    public CustomAdapter(Context context) {
      inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
      return 4;
    }

    public Object getItem(int position) {
      return position;
    }

    public long getItemId(int position) {
      return position;
    }

    public View getView(int arg0, View convertview, ViewGroup arg2) {
      ViewHolder viewHolder;
      if (convertview == null) {
        convertview = inflater.inflate(R.layout.routine_act, null);
        viewHolder = new ViewHolder();
        viewHolder.spinner =
          (Spinner) convertview.findViewById(R.id.act_spinner);
        viewHolder.spinner.setAdapter(adapter);
        convertview.setTag(viewHolder);
      } else {
        viewHolder = (ViewHolder) convertview.getTag();
      }
      return convertview;
    }

    public class ViewHolder {

      Spinner spinner;
    }
  }
}
