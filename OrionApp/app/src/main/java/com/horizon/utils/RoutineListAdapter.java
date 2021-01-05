package com.horizon.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class RoutineListAdapter extends ArrayAdapter<SingleConnection> {

    private Context context;
    private int res;

    public RoutineListAdapter(Context context, int res, ArrayList<SingleConnection> singleConnections) {
        super(context, res, singleConnections);
        this.context = context;
        this.res = res;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        return convertView;
    }
}

