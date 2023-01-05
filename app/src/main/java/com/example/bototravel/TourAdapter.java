package com.example.bototravel;

import android.content.Context;
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
    ArrayList<Object> tours;

    public TourAdapter(Context context, ArrayList<Object> tours) {
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
        final Tour tour = (Tour) tours.get(position);
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

            tv_id = itemView.findViewById(R.id.tv_id);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_entity = itemView.findViewById(R.id.tv_entity);
            iv_status = itemView.findViewById(R.id.iv_status);
        }
    }
}
