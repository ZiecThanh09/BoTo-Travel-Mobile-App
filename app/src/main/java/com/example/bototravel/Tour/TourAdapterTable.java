package com.example.bototravel.Tour;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bototravel.R;

import java.util.ArrayList;

public class TourAdapterTable extends RecyclerView.Adapter<TourAdapterTable.TourTableViewHolder> {
    Context context;
    ArrayList<Tour> tours;
    ArrayList<Tour> toursOld;


    public TourAdapterTable(Context context, ArrayList<Tour> tours) {
        this.context = context;
        this.tours = tours;
        this.toursOld = tours;

    }

    @NonNull
    @Override
    public TourTableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tour_item_table, parent,
                false);
        return new TourTableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TourTableViewHolder holder, int position) {
        final Tour tour = tours.get(position);
        String entity = tour.getEntity() + "/" + tour.getEntity_total();

        holder.tv_id.setText(String.valueOf(tour.getId()));
        holder.tv_name.setText(String.valueOf(tour.getName()));
        holder.tv_entity.setText(entity);

        if (tour.getStatus() == 1) {
            holder.iv_status.setImageResource(R.drawable.img_accept);
        } else if (tour.getStatus() == 2) {
            holder.iv_status.setImageResource(R.drawable.img_deny);
        } else if (tour.getStatus() == 3) {
            holder.iv_status.setImageResource(R.drawable.img_approve);
        }

        if (tour.getEntity() == tour.getEntity_total()) {
            holder.tv_entity.setTextColor(Color.parseColor("#FF0000"));
        }
    }

    @Override
    public int getItemCount() {
        return tours.size();
    }

    public static class TourTableViewHolder extends RecyclerView.ViewHolder {
        TextView tv_id, tv_name, tv_entity;
        ImageView iv_status;
        public TourTableViewHolder(View itemView) {
            super(itemView);

            tv_id = itemView.findViewById(R.id.tv_table_id);
            tv_name = itemView.findViewById(R.id.tv_table_name);
            tv_entity = itemView.findViewById(R.id.tv_table_entity);
            iv_status = itemView.findViewById(R.id.iv_table_status);
        }
    }
}
