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

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedsViewHolder> {
    Context context;
    ArrayList<Feed> feeds;

    public FeedAdapter(Context context, ArrayList<Feed> feeds) {
        this.context = context;
        this.feeds = feeds;
    }

    @NonNull
    @Override
    public FeedsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newfeed_item_feed, parent,
                false);
        return new FeedsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedsViewHolder holder, int position) {
        final Feed feed = feeds.get(position);

        holder.tv_userName.setText(String.valueOf(feed.getName()));
        holder.tv_time.setText(String.valueOf(feed.getTime()));
        holder.tv_status.setText(String.valueOf(feed.getStatus()));
        holder.tv_likes.setText(String.valueOf(feed.getLikes()));
        holder.tv_comments.setText(String.valueOf(feed.getComments()));
        holder.tv_share.setText(String.valueOf(feed.getShares()));
        holder.imgView_proPic.setImageResource(feed.getProPic());

        if (feed.getTime().equals("Vừa xong")) {
            holder.tv_time.setTextColor(Color.parseColor("#44ADE5"));
        }

        if (feed.getPostPic() == 0) {
            holder.imgView_postPic.setVisibility(View.GONE);
        } else {
            holder.imgView_postPic.setVisibility(View.VISIBLE);
            holder.imgView_postPic.setImageResource(feed.getPostPic());
        }
    }

    @Override
    public int getItemCount() {
        return feeds.size();
    }

    public class FeedsViewHolder extends RecyclerView.ViewHolder {
        TextView tv_userName, tv_time, tv_status, tv_likes, tv_comments, tv_share;
        ImageView imgView_proPic, imgView_postPic;
        public FeedsViewHolder(View itemView) {
            super(itemView);

            imgView_proPic = itemView.findViewById(R.id.imgView_proPic);
            imgView_postPic = itemView.findViewById(R.id.imgView_postPic);

            tv_userName = itemView.findViewById(R.id.tv_userName);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_status = itemView.findViewById(R.id.tv_feed_status);
            tv_likes = itemView.findViewById(R.id.tv_like);
            tv_comments = itemView.findViewById(R.id.tv_comment);
            tv_share = itemView.findViewById(R.id.tv_share);
        }
    }
}
