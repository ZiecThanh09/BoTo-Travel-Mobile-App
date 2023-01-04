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

        // Stories Bar
        List<Story> stories = new ArrayList<>();
        stories.add(new Story(true, "ZiecThanh"));
        stories.add(new Story(true, "Tkien"));
        stories.add(new Story(false, "QHai"));
        stories.add(new Story(true, "MSon"));

        StoriesAdapter adapter = new StoriesAdapter(stories, this);
        storiesBar.setAdapter(adapter);
        storiesBar.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL,
                false));

        storiesBar.addItemDecoration(new StoriesDecoration(40));

        // New feeds
        ArrayList<Feed> feeds = new ArrayList<>();
        feeds.add(new Feed(1, 900, 14, 9,
                R.drawable.ic_launcher_foreground, R.drawable.img_status, "ZiecThanh",
                "1 giờ", "TEST"));
        feeds.add(new Feed(2, 999, 13, 2,
                R.drawable.ic_launcher_foreground, R.drawable.img_status, "QHai",
                "2 giờ", "Test"));
        feeds.add(new Feed(3, 0, 0, 0,
                R.drawable.ic_launcher_foreground, R.drawable.img_status, "Tkien",
                "1 phút", "test"));
        feeds.add(new Feed(4, 999, 13, 2,
                R.drawable.ic_launcher_foreground, R.drawable.img_status, "MSon",
                "Vừa xong", "tEsT"));

        FeedsAdapter feedsAdapter = new FeedsAdapter(this, feeds);
        newFeeds.setAdapter(feedsAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        newFeeds.setLayoutManager(layoutManager);

        feedsAdapter.notifyDataSetChanged();
    }
}