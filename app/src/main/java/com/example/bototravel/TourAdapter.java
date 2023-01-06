package com.example.bototravel;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.TourViewHolder>{
    Context context;
    ArrayList<Tour> tours;

    public TourAdapter(Context context, ArrayList<Tour> tours) {
        this.context = context;
        this.tours = tours;
    }

    @NonNull
    @Override
    public TourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tour, parent,
                false);
        return new TourAdapter.TourViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TourViewHolder holder, int position) {
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

        if (tour.getEntity() == tour.entity_total) {
            holder.tv_entity.setText(entity);
            holder.tv_entity.setTextColor(Color.parseColor("#FF0000"));
        }
    }

    @Override
    public int getItemCount() {
        return tours.size();
    }

    public class TourViewHolder extends RecyclerView.ViewHolder {
        TextView tv_id, tv_name, tv_entity;
        ImageView iv_status;
        public TourViewHolder(View itemView) {
            super(itemView);

            tv_id = itemView.findViewById(R.id.tv_table_id);
            tv_name = itemView.findViewById(R.id.tv_table_name);
            tv_entity = itemView.findViewById(R.id.tv_table_entity);
            iv_status = itemView.findViewById(R.id.iv_table_status);
        }
    }
}
