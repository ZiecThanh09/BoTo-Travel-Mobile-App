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
    TableMode[] tableModes;

    public SpinnerModeAdapter(Context context, TableMode[] tableModes) {
        this.context = context;
        this.tableModes = tableModes;
    }

    @Override
    public int getCount() {
        return tableModes.length;
    }

    @Override
    public Object getItem(int position) {
        return tableModes[position];
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

        tv_mode.setText(tableModes[position].getModeName());

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.tour_item_spinner_mode, null);
        TextView modeNames = convertView.findViewById(R.id.tv_modeView);
        modeNames.setText(tableModes[position].getModeName());
        return convertView;
    }
}
