package com.example.bototravel;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TourCalendarAdapter extends RecyclerView.Adapter<TourCalendarAdapter.TourCalendarViewHolder> {
    Context context;
    ArrayList<Tour> tours;

    public TourCalendarAdapter(Context context, ArrayList<Tour> tours) {
        this.context = context;
        this.tours = tours;
    }

    @NonNull
    @Override
    public TourCalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tour_item_calendar,
                parent, false);
        return new TourCalendarAdapter.TourCalendarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TourCalendarViewHolder holder, int position) {
        final Tour tour = tours.get(position);
        String ID = "#" + tour.getId();

        holder.tv_name.setText(String.valueOf(tour.getName()));
        holder.tv_id.setText(ID);
        holder.tv_start.setText(String.valueOf(tour.getStart()));
        holder.tv_end.setText(String.valueOf(tour.getEnd()));
        holder.tv_entityAvailable.setText(String.valueOf(tour.getEntity()));
        holder.tv_entityTotal.setText(String.valueOf(tour.getEntity_total()));

        if (tour.getStatus() == 1) {
            holder.tv_status.setText("Đang mở bán");
            holder.tv_status.setTextColor(Color.parseColor("#44ADE5"));
            holder.view_status.setBackgroundResource(R.color.blue_app);
        } else if (tour.getStatus() == 2){
            holder.tv_status.setText("Hết vé");
            holder.tv_status.setTextColor(Color.parseColor("#FF0000"));
            holder.view_status.setBackgroundResource(R.color.red);
        } else {
            holder.tv_status.setText("Đang chờ duyệt");
            holder.tv_status.setTextColor(Color.parseColor("#8B8B8B"));
            holder.view_status.setBackgroundResource(R.color.grey);
        }
    }

    @Override
    public int getItemCount() {
        return tours.size();
    }

    public static class TourCalendarViewHolder extends RecyclerView.ViewHolder {
        TextView tv_id, tv_name, tv_start, tv_end, tv_entityAvailable, tv_entityTotal, tv_status;
        View view_status;
        public TourCalendarViewHolder(View itemView) {
            super(itemView);

            tv_id = itemView.findViewById(R.id.tv_calendar_id);
            tv_name = itemView.findViewById(R.id.tv_calendar_name);
            tv_start = itemView.findViewById(R.id.tv_calendar_locationStart);
            tv_end = itemView.findViewById(R.id.tv_calendar_locationEnd);
            tv_entityAvailable = itemView.findViewById(R.id.tv_calendar_entityAvailable);
            tv_entityTotal = itemView.findViewById(R.id.tv_calendar_entityTotal);
            tv_status = itemView.findViewById(R.id.tv_calendar_status);
            view_status = itemView.findViewById(R.id.view_statusTour);
        }
    }
}
