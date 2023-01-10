package com.example.bototravel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SpinnerModeAdapter extends BaseAdapter {
    Context context;
    SpinnerMode[] spinnerModes;

    public SpinnerModeAdapter(Context context, SpinnerMode[] spinnerModes) {
        this.context = context;
        this.spinnerModes = spinnerModes;
    }

    @Override
    public int getCount() {
        return spinnerModes.length;
    }

    @Override
    public Object getItem(int position) {
        return spinnerModes[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder") View view = LayoutInflater.from(context)
                .inflate(R.layout.tour_item_spinner_mode, parent, false);

        TextView tv_mode = view.findViewById(R.id.tv_modeView);
        tv_mode.setText(spinnerModes[position].getModeName());

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.tour_item_spinner_mode, null);
        TextView modeNames = convertView.findViewById(R.id.tv_modeView);
        modeNames.setText(spinnerModes[position].getModeName());
        return convertView;
    }
}
