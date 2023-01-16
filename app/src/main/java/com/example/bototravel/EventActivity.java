package com.example.bototravel;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EventActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event);

        findViewByIds();
    }

    private void findViewByIds() {
        ImageButton ibFeed = findViewById(R.id.imgBtn_home);
        ImageButton ibTour = findViewById(R.id.imgBtn_book);
        ImageButton ibUser = findViewById(R.id.imgBtn_user);
        RecyclerView events = findViewById(R.id.rcv_detailedEvents);

        // Animation Button
        ibFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentFeed = new Intent(EventActivity.this, FeedActivity.class);
                startActivity(intentFeed);
                overridePendingTransition(R.anim.anim_move_in_left, R.anim.anim_move_out_right);
            }
        });

        ibTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTour = new Intent(EventActivity.this, TourActivity.class);
                startActivity(intentTour);
                overridePendingTransition(R.anim.anim_move_in_left, R.anim.anim_move_out_right);
            }
        });

        ibUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentUser = new Intent(EventActivity.this, AccountActivity.class);
                startActivity(intentUser);
                overridePendingTransition(R.anim.anim_move_in_right, R.anim.anim_move_out_left);
            }
        });

        // Events
        ArrayList<Event> eventArrayList = new ArrayList<>();
        eventArrayList.add(new Event(1, 1, "Tên sự kiện 1",
                "8/1/2023", "8/2/2023", "Tất cả nhân viên"));
        eventArrayList.add(new Event(2, 2, "Tên sự kiện 1",
                "8/1/2023", "8/2/2023", "Tất cả nhân viên"));
        eventArrayList.add(new Event(3, 3, "Tên sự kiện 1",
                "8/1/2023", "8/2/2023", "Tất cả nhân viên"));
        eventArrayList.add(new Event(4, 2, "Tên sự kiện 1",
                "8/1/2023", "8/2/2023", "Tất cả nhân viên"));
        eventArrayList.add(new Event(5, 1, "Tên sự kiện 1",
                "8/1/2023", "8/2/2023", "Tất cả nhân viên"));
        eventArrayList.add(new Event(6, 3, "Tên sự kiện 1",
                "8/1/2023", "8/2/2023", "Tất cả nhân viên"));

        EventAdapter eventAdapter = new EventAdapter(this, eventArrayList);
        events.setAdapter(eventAdapter);
        events.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,
                false));
        events.setNestedScrollingEnabled(false);

        eventAdapter.notifyDataSetChanged();

    }
}
