package com.example.bototravel;

public enum TourSpinnerMode {
    TableMode1("Bảng"),
    TableMode2("Lịch");

    private String modeName;

    TourSpinnerMode(String modeName) {
        this.modeName = modeName;
    }

    public String getModeName() {
        return modeName;
    }
}
