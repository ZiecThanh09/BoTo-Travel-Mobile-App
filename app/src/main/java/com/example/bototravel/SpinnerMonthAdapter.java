package com.example.bototravel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SpinnerMonthAdapter extends BaseAdapter {
    Context context;
    SpinnerMonth[] spinnerMonths;

    public SpinnerMonthAdapter(Context context, SpinnerMonth[] spinnerMonths) {
        this.context = context;
        this.spinnerMonths = spinnerMonths;
    }

    @Override
    public int getCount() {
        return spinnerMonths.length;
    }

    @Override
    public Object getItem(int position) {
        return spinnerMonths[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder") View view = LayoutInflater.from(context)
                .inflate(R.layout.tour_item_spinner_time, parent, false);

        TextView month = view.findViewById(R.id.tv_time);
        month.setText(spinnerMonths[position].getMonth());

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.tour_item_spinner_time, null);
        TextView modeNames = convertView.findViewById(R.id.tv_time);
        modeNames.setText(spinnerMonths[position].getMonth());
        return convertView;
    }
}
