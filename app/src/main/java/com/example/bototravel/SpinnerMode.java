package com.example.bototravel;

public enum SpinnerMode {
    TableMode1("Bảng"),
    TableMode2("Lịch");

    private String modeName;

    SpinnerMode(String modeName) {
        this.modeName = modeName;
    }

    public String getModeName() {
        return modeName;
    }
}
