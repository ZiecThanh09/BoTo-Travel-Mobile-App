package com.example.bototravel;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TourFragmentTable extends Fragment {

    private RecyclerView toursList;
    private ArrayList<Tour> tours;
    private TourAdapterTable tourTableAdapter;
    private int month;

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

        ItemViewModel itemViewModel = new ViewModelProvider(requireActivity()).get(ItemViewModel.class);
        itemViewModel.getSelectedItem().observe(requireActivity(), item -> month = item);

        tourTableAdapter = new TourAdapterTable(getContext(), tours);
        getSearchData(month);

        tourTableAdapter.notifyDataSetChanged();
    }

    private void getSearchData(int month) {
        ArrayList<Tour> tourSearch = new ArrayList<>();
        if (month > 12) {
            tourTableAdapter = new TourAdapterTable(getContext(), tours);
        } else {
            for (Tour tour : tours) {
                if (tour.getStartMonth() == month) {
                    tourSearch.add(tour);
                }
            }
            tourTableAdapter = new TourAdapterTable(getContext(), tourSearch);
        }
        toursList.setAdapter(tourTableAdapter);

    }

    private void dataInitialize() {
        // Table Tours
        tours = new ArrayList<>();
        tours.add(new Tour(1, "Hà Nội Tour", 20, 30, 1, 1));
        tours.add(new Tour(2, "TP.HCM Tour", 14, 14, 1, 1));
        tours.add(new Tour(3, "Đà Nẵng Tour", 12, 30, 2, 1));
        tours.add(new Tour(4, "Đà Lạt Tour 1", 0, 45, 3, 4));
        tours.add(new Tour(5, "Đà Lạt Tour 2", 0, 45, 3, 5));
        tours.add(new Tour(6, "Đà Lạt Tour 3", 0, 45, 3, 6));
        tours.add(new Tour(7, "Đà Lạt Tour 4", 0, 45, 3, 7));
        tours.add(new Tour(8, "Đà Lạt Tour 5", 0, 45, 3, 8));
        tours.add(new Tour(9, "Đà Lạt Tour 6", 0, 45, 3, 9));
        tours.add(new Tour(10, "Đà Lạt Tour 7", 0, 45, 3, 10));
        tours.add(new Tour(11, "Đà Lạt Tour 8", 12, 45, 3, 11));
        tours.add(new Tour(12, "Đà Lạt Tour 9", 0, 45, 3, 12));
    }
}