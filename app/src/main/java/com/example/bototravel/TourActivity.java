package com.example.bototravel;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TourActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.booktours_table);

        findViewByIds();

        // Animation Button
        ibFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                // overridePendingTransition(R.anim.anim_move_in_left, R.anim.anim_move_out_right);
            }
        });
    }

    private ImageButton ibFeed;

    @SuppressLint("NotifyDataSetChanged")
    private void findViewByIds() {
        ibFeed = findViewById(R.id.imgBtn_home);
        Spinner snMode = findViewById(R.id.sn_modeView);
        RecyclerView toursList = findViewById(R.id.rcv_tours);

        // Tours
        ArrayList<Tour> tours = new ArrayList<>();
        tours.add(new Tour(1, "Hà Nội Tour", 20, 30, 1));
        tours.add(new Tour(2, "TP.HCM Tour", 14, 14, 1));
        tours.add(new Tour(3, "Đà Nẵng Tour", 12, 30, 2));
        tours.add(new Tour(4, "Đà Lạt Tour", 0, 45, 3));
        tours.add(new Tour(5, "Đà Lạt Tour", 0, 45, 3));
        tours.add(new Tour(6, "Đà Lạt Tour", 0, 45, 3));
        tours.add(new Tour(7, "Đà Lạt Tour", 0, 45, 3));
        tours.add(new Tour(8, "Đà Lạt Tour", 0, 45, 3));
        tours.add(new Tour(9, "Đà Lạt Tour", 0, 45, 3));
        tours.add(new Tour(10, "Đà Lạt Tour", 0, 45, 3));
        tours.add(new Tour(11, "Đà Lạt Tour", 0, 45, 3));

        TourAdapter tourAdapter = new TourAdapter(this, tours);
        toursList.setAdapter(tourAdapter);
        toursList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,
                false));

        tourAdapter.notifyDataSetChanged();
    }
}
