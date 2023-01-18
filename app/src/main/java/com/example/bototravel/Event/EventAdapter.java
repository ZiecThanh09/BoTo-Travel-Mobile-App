package com.example.bototravel.Event;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bototravel.R;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    Context context;
    ArrayList<Event> events;

    public EventAdapter(Context context, ArrayList<Event> events) {
        this.context = context;
        this.events = events;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item,
                parent, false);
        return new EventAdapter.EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        final Event event = events.get(position);
        String ID = "#" + event.getId();

        holder.id.setText(ID);
        holder.name.setText(String.valueOf(event.getName()));
        holder.start.setText(String.valueOf(event.getStart()));
        holder.end.setText(String.valueOf(event.getEnd()));
        holder.participant.setText(String.valueOf(event.getParticipant()));

        if (event.getStatus() == 1) {
            holder.status.setText("Đang diễn ra");
            holder.status.setTextColor(Color.parseColor("#44ADE5"));
            holder.statusBar.setBackgroundResource(R.color.blue_app);
        } else if (event.getStatus() == 2){
            holder.status.setText("Đã kết thúc");
            holder.status.setTextColor(Color.parseColor("#FF0000"));
            holder.statusBar.setBackgroundResource(R.color.red);
        } else {
            holder.status.setText("Đang chờ duyệt");
            holder.status.setTextColor(Color.parseColor("#8B8B8B"));
            holder.statusBar.setBackgroundResource(R.color.grey);
        }
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        TextView id, name, start, end, participant, status;
        View statusBar;
        public EventViewHolder(View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.tv_event_id);
            name = itemView.findViewById(R.id.tv_event_name);
            start = itemView.findViewById(R.id.tv_event_dateStart);
            end = itemView.findViewById(R.id.tv_event_dateEnd);
            participant = itemView.findViewById(R.id.tv_event_participant);
            status = itemView.findViewById(R.id.tv_event_status);
            statusBar = itemView.findViewById(R.id.view_statusEvent);
        }
    }
}
