package com.example.bototravel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TableModeAdapter extends BaseAdapter {
    Context context;
    TableMode[] tableModes;
    LayoutInflater inflater;

    public TableModeAdapter(Context context, TableMode[] tableModes) {
        this.context = context;
        this.tableModes = tableModes;
        inflater = (LayoutInflater.from(context));
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
        return null;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.item_mode_spinner, null);
        TextView modeNames = convertView.findViewById(R.id.sp_mode_text);
        modeNames.setText(tableModes[position].getModeName());
        return convertView;
    }
}
