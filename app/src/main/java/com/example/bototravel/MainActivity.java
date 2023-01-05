package com.example.bototravel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.newfeeds);

        findViewsByIds();

    }

    @SuppressLint("NotifyDataSetChanged")
    private void findViewsByIds() {
        RecyclerView storiesBar = findViewById(R.id.storiesBar);
        RecyclerView newFeeds = findViewById(R.id.newFeeds);
        RecyclerView toursList = findViewById(R.id.rcv_tours);

        // Stories Bar
        List<Story> stories = new ArrayList<>();
        stories.add(new Story(false, "ZiecThanh"));
        stories.add(new Story(true, "Tkien"));
        stories.add(new Story(false, "QHai"));
        stories.add(new Story(true, "MSon"));

        StoriesAdapter adapter = new StoriesAdapter(stories, this);
        storiesBar.setAdapter(adapter);
        storiesBar.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL,
                false));

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

        FeedsAdapter feedsAdapter = new FeedsAdapter(this, feeds);
        newFeeds.setAdapter(feedsAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        newFeeds.setLayoutManager(layoutManager);

        feedsAdapter.notifyDataSetChanged();

        // Tours
        ArrayList<Object> tours = new ArrayList<>();
        tours.add(new String("ID") + new String("Tên tour") +
                new String("Số lượng") + new String("Tình trạng"));
        tours.add(new Tour(1, "Hà Nội Tour", 20, 30, 1));
        tours.add(new Tour(2, "TP.HCM Tour", 14, 14, 1));
        tours.add(new Tour(3, "Đà Nẵng Tour", 12, 30, 2));
        tours.add(new Tour(3, "Đà Lạt Tour", 0, 45, 3));
    }
}