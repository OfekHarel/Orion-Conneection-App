package com.horizon.utils.conn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.horizon.OrionConnection.R;
import java.util.ArrayList;

/**
 * An adpter to present the single connection in a list view.
 */
public class ConnectionListAdapter extends ArrayAdapter<SingleConnection> {

  private Context context;
  private int res;

  public ConnectionListAdapter(
    Context context,
    int res,
    ArrayList<SingleConnection> singleConnections
  ) {
    super(context, res, singleConnections);
    this.context = context;
    this.res = res;
  }

  @NonNull
  @Override
  public View getView(
    int position,
    @Nullable View convertView,
    @NonNull ViewGroup parent
  ) {
    String name = getItem(position).getName();

    LayoutInflater inflater = LayoutInflater.from(this.context);
    convertView = inflater.inflate(this.res, parent, false);

    TextView textView = convertView.findViewById(R.id.conn_name);
    textView.setText(name);

    ImageView imageView = convertView.findViewById(R.id.conn_image);
      imageView.setImageDrawable(
        ContextCompat.getDrawable(context, R.drawable.ic_computer)
      );

    return convertView;
  }
}
