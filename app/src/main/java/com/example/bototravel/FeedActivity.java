package com.example.bototravel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.newfeeds);

        findViewsByIds();

        // Animation Button
        ibTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTour = new Intent(FeedActivity.this, TourActivity.class);
                startActivity(intentTour);
                // overridePendingTransition(R.anim.anim_move_in_right, R.anim.anim_move_out_left);
            }
        });
    }

    private ImageButton ibTour, ibEvent, ibUser;

    @SuppressLint("NotifyDataSetChanged")
    private void findViewsByIds() {
        ibTour = findViewById(R.id.imgBtn_book);
        ibEvent = findViewById(R.id.imgBtn_event);
        ibUser = findViewById(R.id.imgBtntn_user);
        RecyclerView storiesBar = findViewById(R.id.storiesBar);
        RecyclerView newFeeds = findViewById(R.id.newFeeds);



        // Stories Bar
        List<Story> stories = new ArrayList<>();
        stories.add(new Story(false, "ZiecThanh"));
        stories.add(new Story(true, "Tkien"));
        stories.add(new Story(false, "QHai"));
        stories.add(new Story(true, "MSon"));

        StoryAdapter storyAdapter = new StoryAdapter(stories, this);
        storiesBar.setAdapter(storyAdapter);
        storiesBar.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL,
                false));

        storyAdapter.notifyDataSetChanged();

        // New feeds
        ArrayList<Feed> feeds = new ArrayList<>();
        feeds.add(new Feed(1, 900, 14, 9,
                R.drawable.ic_launcher_foreground, R.drawable.img_status, "ZiecThanh",
                "1 giờ", "TEST"));
        feeds.add(new Feed(2, 999, 13, 2,
                R.drawable.ic_launcher_foreground, R.drawable.img_status_1, "QHai",
                "2 giờ", "Test"));
        feeds.add(new Feed(3, 0, 0, 0,
                R.drawable.ic_launcher_foreground, R.drawable.img_status_2, "Tkien",
                "1 phút", "test"));
        feeds.add(new Feed(4, 999, 13, 2,
                R.drawable.ic_launcher_foreground, R.drawable.img_status_3, "MSon",
                "Vừa xong", "tEsT"));

        FeedAdapter feedAdapter = new FeedAdapter(this, feeds);
        newFeeds.setAdapter(feedAdapter);
        newFeeds.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,
                false));

        feedAdapter.notifyDataSetChanged();
    }
}