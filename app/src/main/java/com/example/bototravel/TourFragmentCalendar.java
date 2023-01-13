package com.example.bototravel;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TourFragmentCalendar extends Fragment {
    private RecyclerView toursList;
    private ArrayList<Tour> tours;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tour_fragment_calendar, container, false);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataInitialize();

        toursList = view.findViewById(R.id.rcv_detailedTours);
        toursList.setLayoutManager(new LinearLayoutManager(getContext()));
        toursList.setNestedScrollingEnabled(false);

        TourAdapterCalendar tourAdapterCalendar = new TourAdapterCalendar(getContext(), tours);
        toursList.setAdapter(tourAdapterCalendar);
        tourAdapterCalendar.notifyDataSetChanged();
    }

    private void dataInitialize() {
        // Calendar Tours
        tours = new ArrayList<>();
        tours.add(new Tour(1, "Hà Nội Tour", "TP.HCM", "Hà nội", 14, 30, 1));
        tours.add(new Tour(2, "Hà Nội Tour", "TP.HCM", "Hà nội", 14, 14, 2));
        tours.add(new Tour(3, "Hà Nội Tour", "TP.HCM", "Hà nội", 0, 30, 3));
        tours.add(new Tour(4, "Hà Nội Tour", "TP.HCM", "Hà nội", 14, 30, 1));
        tours.add(new Tour(5, "Hà Nội Tour", "TP.HCM", "Hà nội", 14, 30, 1));
        tours.add(new Tour(6, "Hà Nội Tour", "TP.HCM", "Hà nội", 14, 30, 1));
        tours.add(new Tour(7, "Hà Nội Tour", "TP.HCM", "Hà nội", 14, 30, 1));
        tours.add(new Tour(8, "Hà Nội Tour", "TP.HCM", "Hà nội", 14, 30, 1));
    }
}
