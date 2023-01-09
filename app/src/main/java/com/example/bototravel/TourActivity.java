package com.example.bototravel;

import android.annotation.SuppressLint;
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

    private List<String> modeNames;

    @SuppressLint("NotifyDataSetChanged")
    private void findViewByIds() {
        ImageButton ibFeed = findViewById(R.id.imgBtn_home);
        FragmentTourTable fragmentTourTable;
        FragmentTourCalendar fragmentTourCalendar;
        Spinner snTime = findViewById(R.id.sn_time);
        Spinner snMode = findViewById(R.id.sn_modeView);

        RecyclerView toursList = findViewById(R.id.rcv_tours);

        // Animation Button
        ibFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                // overridePendingTransition(R.anim.anim_move_in_left, R.anim.anim_move_out_right);
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




//        // ViewPager
//        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(),
//                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
//        vpAdapter.addFragment(new FragmentTourTable(), "TOUR TABLE MODE VIEW");
//        vpAdapter.addFragment(new FragmentTourCalendar(), "TOUR CALENDAR MODE VIEW");
//        viewPager.setAdapter(vpAdapter);

//        // Tours
//        ArrayList<Tour> tours = new ArrayList<>();
//        tours.add(new Tour(1, "Hà Nội Tour", 20, 30, 1));
//        tours.add(new Tour(2, "TP.HCM Tour", 14, 14, 1));
//        tours.add(new Tour(3, "Đà Nẵng Tour", 12, 30, 2));
//        tours.add(new Tour(4, "Đà Lạt Tour", 0, 45, 3));
//        tours.add(new Tour(5, "Đà Lạt Tour", 0, 45, 3));
//        tours.add(new Tour(6, "Đà Lạt Tour", 0, 45, 3));
//        tours.add(new Tour(7, "Đà Lạt Tour", 0, 45, 3));
//        tours.add(new Tour(8, "Đà Lạt Tour", 0, 45, 3));
//        tours.add(new Tour(9, "Đà Lạt Tour", 0, 45, 3));
//        tours.add(new Tour(10, "Đà Lạt Tour", 0, 45, 3));
//        tours.add(new Tour(11, "Đà Lạt Tour", 0, 45, 3));
//
//        TourAdapter tourAdapter = new TourAdapter(this, tours);
//        toursList.setAdapter(tourAdapter);
//        toursList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,
//                false));
//
//        tourAdapter.notifyDataSetChanged();
    }

    private void selectFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fl_viewMode, fragment);
        fragmentTransaction.commit();
    }
}
