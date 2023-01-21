package com.example.bototravel.User;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.bototravel.Event.EventActivity;
import com.example.bototravel.Feed.FeedActivity;
import com.example.bototravel.R;
import com.example.bototravel.Tour.TourActivity;
import com.google.android.material.tabs.TabLayout;

public class UserActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);

        findViewByIds();
    }

    private void findViewByIds() {
        ImageButton ibFeed = findViewById(R.id.imgBtn_home);
        ImageButton ibTour = findViewById(R.id.imgBtn_book);
        ImageButton ibEvent = findViewById(R.id.imgBtn_event);
        TabLayout tabLayout = findViewById(R.id.tl_info);
        ViewPager viewPager = findViewById(R.id.vp_info);

        // Animation Button
        ibFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentFeed = new Intent(UserActivity.this, FeedActivity.class);
                startActivity(intentFeed);
                overridePendingTransition(R.anim.anim_move_in_left, R.anim.anim_move_out_right);
            }
        });

        ibTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTour = new Intent(UserActivity.this, TourActivity.class);
                startActivity(intentTour);
                overridePendingTransition(R.anim.anim_move_in_left, R.anim.anim_move_out_right);
            }
        });

        ibEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEvent = new Intent(UserActivity.this, EventActivity.class);
                startActivity(intentEvent);
                overridePendingTransition(R.anim.anim_move_in_left, R.anim.anim_move_out_right);
            }
        });

        // TabLayout and ViewPager
        UserAdapterViewPager userAdapterViewPager = new UserAdapterViewPager(
                getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(userAdapterViewPager);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#44ADE5"));
    }
}
