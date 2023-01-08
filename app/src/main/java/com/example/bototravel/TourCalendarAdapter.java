package com.example.bototravel;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TourCalendarAdapter extends RecyclerView.Adapter<TourCalendarAdapter.TourCalendarViewHolder> {



    @NonNull
    @Override
    public TourCalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TourCalendarViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
