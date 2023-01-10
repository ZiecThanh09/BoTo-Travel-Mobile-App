package com.example.bototravel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TourTableAdapter extends RecyclerView.Adapter<TourTableAdapter.TourTableViewHolder> implements Filterable {
    private Context context;
    private ArrayList<Tour> tours;
    private ArrayList<Tour> toursOld;


    public TourTableAdapter(Context context, ArrayList<Tour> tours) {
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

        if (tour.getEntity() == tour.entity_total) {
            holder.tv_entity.setText(entity);
            holder.tv_entity.setTextColor(Color.parseColor("#FF0000"));
        }
    }

    @Override
    public int getItemCount() {
        return tours.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                int monthSearch = Integer.parseInt(constraint.toString());
                if (monthSearch >=0 && monthSearch <= 12) {
                    ArrayList<Tour> arrayList = new ArrayList<>();
                    for (Tour tour : toursOld) {
                        if (tour.getStartMonth() == monthSearch) {
                            arrayList.add(tour);
                        }
                    }
                    tours = arrayList;
                } else tours = toursOld;

                FilterResults filterResults = new FilterResults();
                filterResults.values = tours;

                return filterResults;
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                tours = (ArrayList<Tour>) results.values;
                notifyDataSetChanged();
            }
        };
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
