package com.example.bototravel.Tour;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.bototravel.Chat.BaseActivity;
import com.example.bototravel.User.UserActivity;
import com.example.bototravel.Event.EventActivity;
import com.example.bototravel.Feed.FeedActivity;
import com.example.bototravel.R;

import java.util.Calendar;

public class TourActivity extends BaseActivity {
    private int month;
    private TourItemViewModel tourItemViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tour);

        findViewByIds();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void findViewByIds() {
        ImageButton ibFeed = findViewById(R.id.imgBtn_home);
        ImageButton ibEvent = findViewById(R.id.imgBtn_event);
        ImageButton ibUser = findViewById(R.id.imgBtn_user);
        TourFragmentTable fragmentTourTable;
        TourFragmentCalendar fragmentTourCalendar;
        Spinner snTime = findViewById(R.id.sn_time);
        Spinner snMode = findViewById(R.id.sn_modeView);

        // Animation Button
        ibFeed.setOnClickListener(v -> {
            Intent intentFeed = new Intent(TourActivity.this, FeedActivity.class);
            startActivity(intentFeed);
            overridePendingTransition(R.anim.anim_move_in_left, R.anim.anim_move_out_right);
        });

        ibEvent.setOnClickListener(v -> {
            Intent intentEvent = new Intent(TourActivity.this, EventActivity.class);
            startActivity(intentEvent);
            overridePendingTransition(R.anim.anim_move_in_right, R.anim.anim_move_out_left);
        });

        ibUser.setOnClickListener(v -> {
            Intent intentUser = new Intent(TourActivity.this, UserActivity.class);
            startActivity(intentUser);
            overridePendingTransition(R.anim.anim_move_in_right, R.anim.anim_move_out_left);
        });

        // Fragment
        fragmentTourCalendar = new TourFragmentCalendar();
        fragmentTourTable = new TourFragmentTable();

        // Spinner Mode View
        TourSpinnerModeAdapter spinnerModeAdapter = new TourSpinnerModeAdapter(
                this, TourSpinnerMode.values());
        snMode.setAdapter(spinnerModeAdapter);
        snTime.setSelection(0);
        snMode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startFragment(fragmentTourTable);
                        snTime.setEnabled(true);
                        snTime.setBackgroundResource(R.drawable.edit_spinner_bg);
                        break;
                    case 1:
                        startFragment(fragmentTourCalendar);
                        snTime.setEnabled(false);
                        snTime.setBackgroundResource(R.drawable.edit_spinner_block_bg);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Spinner Month
        TourSpinnerMonthAdapter spinnerMonthAdapter = new TourSpinnerMonthAdapter(
                this, TourSpinnerMonth.values());
        snTime.setAdapter(spinnerMonthAdapter);
        snTime.setSelection(Calendar.getInstance().get(Calendar.MONTH));
        snTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position >= 0 && position < TourSpinnerMonth.values().length) {
                    month = position + 1;
                    tourItemViewModel = new ViewModelProvider(TourActivity.this).get(TourItemViewModel.class);
                    tourItemViewModel.setData(month);
                    startFragment(fragmentTourCalendar);
                    startFragment(fragmentTourTable);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void startFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fl_viewMode, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}