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

public class FragmentTourTable extends Fragment {

    private RecyclerView toursList;
    private ArrayList<Tour> tours;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tour_fragment_table, container, false);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataInitialize();

        toursList = view.findViewById(R.id.rcv_tours);
        toursList.setLayoutManager(new LinearLayoutManager(getContext()));

        TourTableAdapter tourTableAdapter = new TourTableAdapter(getContext(), tours);
        toursList.setAdapter(tourTableAdapter);
        tourTableAdapter.notifyDataSetChanged();
    }

    private void dataInitialize() {
        // Table Tours
        tours = new ArrayList<>();
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
        tours.add(new Tour(12, "Đà Lạt Tour", 0, 45, 3));
    }
}
