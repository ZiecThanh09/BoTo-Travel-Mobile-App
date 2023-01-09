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
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TourActivity extends AppCompatActivity {

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
                onBackPressed();
                // overridePendingTransition(R.anim.anim_move_in_left, R.anim.anim_move_out_right);
            }
        });

        ibEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEvent = new Intent(TourActivity.this, EventActivity.class);
                startActivity(intentEvent);
            }
        });

        // Fragment
        fragmentTourCalendar = new FragmentTourCalendar();
        fragmentTourTable = new FragmentTourTable();

        // Spinner
        SpinnerModeAdapter spinnerModeAdapter = new SpinnerModeAdapter(
                this, TableMode.values());
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
    }

    private void selectFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fl_viewMode, fragment);
        fragmentTransaction.commit();
    }
}
