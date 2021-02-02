package com.horizon.utils.conn;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.horizon.OrionConnection.R;
import java.util.ArrayList;

/**
 * An adapter to present the single connection in a list view.
 */
public class ConnectionListAdapter extends ArrayAdapter<SingleConnection> {

  private Context context;
  private int res;

  public ConnectionListAdapter(Context context, int res, ArrayList<SingleConnection> singleConnections) {
    super(context, res, singleConnections);
    this.context = context;
    this.res = res;
  }

  @SuppressLint({"SetTextI18n", "ViewHolder"})
  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

    String name = getItem(position).getName();
    String id = getItem(position).getID();

    LayoutInflater inflater = LayoutInflater.from(this.context);
    convertView = inflater.inflate(this.res, parent, false);

    TextView textViewName = convertView.findViewById(R.id.conn_name);
    textViewName.setText(name);

    TextView textViewID = convertView.findViewById(R.id.conn_id);
    textViewID.setText("ID: " + id);

    return convertView;
  }
}
