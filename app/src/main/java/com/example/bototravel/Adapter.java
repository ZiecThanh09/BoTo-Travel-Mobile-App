package com.example.bototravel;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class Adapter extends ArrayAdapter<data> {
    private Activity context;
    public Adapter(@NonNull Activity context, int layoutID, List<data> objects) {
        super(context, layoutID, objects);
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        if (convertView == null) {
            convertView =
                    LayoutInflater.from(context).inflate(R.layout.dong_danhsachtour, null,
                            false);
        }
        // Get item
        data data = getItem(position);
        // Get view
        TextView tvdi = (TextView)
                convertView.findViewById(R.id.tvdi);
        TextView tvden = (TextView)
                convertView.findViewById(R.id.tvden);
        TextView tvngdi = (TextView)
                convertView.findViewById(R.id.tvngdi);
        TextView tvngve = (TextView)
                convertView.findViewById(R.id.tvngve);
        TextView tvgia = (TextView)
                convertView.findViewById(R.id.tvgia);
        tvdi.setText(data.getDiemdi());
        tvden.setText(data.getDiemden());
        tvngdi.setText(data.getNgaydi());
        tvngve.setText(data.getNgayve());
        tvgia.setText(data.getGia());

        return convertView;
    }
}

