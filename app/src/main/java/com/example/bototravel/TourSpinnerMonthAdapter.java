package com.example.bototravel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TourSpinnerMonthAdapter extends BaseAdapter {
    Context context;
    TourSpinnerMonth[] tourSpinnerMonths;

    public TourSpinnerMonthAdapter(Context context, TourSpinnerMonth[] tourSpinnerMonths) {
        this.context = context;
        this.tourSpinnerMonths = tourSpinnerMonths;
    }

    @Override
    public int getCount() {
        return tourSpinnerMonths.length;
    }

    @Override
    public Object getItem(int position) {
        return tourSpinnerMonths[position];
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
        month.setText(tourSpinnerMonths[position].getMonth());

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.tour_item_spinner_time, null);
        TextView modeNames = convertView.findViewById(R.id.tv_time);
        modeNames.setText(tourSpinnerMonths[position].getMonth());
        return convertView;
    }
}
