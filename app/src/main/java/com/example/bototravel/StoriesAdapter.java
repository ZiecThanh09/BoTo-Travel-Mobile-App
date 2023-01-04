package com.example.bototravel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.StoriesViewHolder> {

    private List<Story> stories;
    private Context context;

    public StoriesAdapter(List<Story> stories, Context context) {
        this.stories = stories;
        this.context = context;
    }

    @NonNull
    @Override
    public StoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_story, parent, false);
        StoriesViewHolder viewHolder = new StoriesViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StoriesViewHolder holder, int position) {
        final Story story = stories.get(position);

        holder.userName.setText(String.valueOf(story.getUserName()));
        if (story.isSeen()) {
            holder.storyOutline.setCardBackgroundColor(context.getResources().
                    getColor(R.color.stroke));
        }
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    public static class StoriesViewHolder extends RecyclerView.ViewHolder {
        private CardView storyOutline;
        private TextView userName;
        public StoriesViewHolder(@NonNull View itemView) {
            super(itemView);

            storyOutline = itemView.findViewById(R.id.storyOutline);
            userName = itemView.findViewById(R.id.username);
        }
    }
}
