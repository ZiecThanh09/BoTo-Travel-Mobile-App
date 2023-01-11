package com.example.bototravel;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.Calendar;

public class TourActivity extends AppCompatActivity {
    private int month;

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
        FragmentTourTable fragmentTourTable;
        FragmentTourCalendar fragmentTourCalendar;
        Spinner snTime = findViewById(R.id.sn_time);
        Spinner snMode = findViewById(R.id.sn_modeView);

        // Animation Button
        ibFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentFeed = new Intent(TourActivity.this, FeedActivity.class);
                startActivity(intentFeed);
                overridePendingTransition(R.anim.anim_move_in_left, R.anim.anim_move_out_right);
            }
        });

        ibEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEvent = new Intent(TourActivity.this, EventActivity.class);
                startActivity(intentEvent);
                // add animation
            }
        });

        // Fragment
        fragmentTourCalendar = new FragmentTourCalendar();
        fragmentTourTable = new FragmentTourTable();

        // Spinner Mode View
        SpinnerModeAdapter spinnerModeAdapter = new SpinnerModeAdapter(
                this, SpinnerMode.values());
        snMode.setAdapter(spinnerModeAdapter);
        snMode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        selectFragment(fragmentTourTable);
                        snTime.setEnabled(true);
                        snTime.setBackgroundResource(R.drawable.edit_spinner_bg);
                        break;
                    case 1:
                        selectFragment(fragmentTourCalendar);
                        snTime.setEnabled(false);
                        snTime.setBackgroundResource(R.drawable.edit_spinner_block_bg);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Spinner Time
        SpinnerMonthAdapter spinnerMonthAdapter = new SpinnerMonthAdapter(
                this, SpinnerMonth.values());
        snTime.setAdapter(spinnerMonthAdapter);
        snTime.setSelection(Calendar.getInstance().get(Calendar.MONTH));
        snTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position >= 0 && position < SpinnerMonth.values().length) {
                    month = position;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void selectFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fl_viewMode, fragment);
        fragmentTransaction.commit();
    }

    public int getMonth() {
        return month + 1;
    }
}
