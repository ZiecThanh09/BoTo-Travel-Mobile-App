package com.example.bototravel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TourSpinnerModeAdapter extends BaseAdapter {
    Context context;
    TourSpinnerMode[] tourSpinnerModes;

    public TourSpinnerModeAdapter(Context context, TourSpinnerMode[] tourSpinnerModes) {
        this.context = context;
        this.tourSpinnerModes = tourSpinnerModes;
    }

    @Override
    public int getCount() {
        return tourSpinnerModes.length;
    }

    @Override
    public Object getItem(int position) {
        return tourSpinnerModes[position];
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
        tv_mode.setText(tourSpinnerModes[position].getModeName());

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.tour_item_spinner_mode, null);
        TextView modeNames = convertView.findViewById(R.id.tv_modeView);
        modeNames.setText(tourSpinnerModes[position].getModeName());
        return convertView;
    }
}
