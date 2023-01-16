package com.example.bototravel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TourItemViewModel extends ViewModel {
    private final MutableLiveData<Integer> selectedItem = new MutableLiveData<Integer>();

    public void setData(Integer item) {
        selectedItem.setValue(item);
    }

    public LiveData<Integer> getSelectedItem() {
        return selectedItem;
    }
}
