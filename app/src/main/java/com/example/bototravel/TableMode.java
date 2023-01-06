package com.example.bototravel;

public enum TableMode {
    TableMode1("Bảng"),
    TableMode2("Lịch");

    private String modeName;

    TableMode(String modeName) {
        this.modeName = modeName;
    }

    public String getModeName() {
        return modeName;
    }
}
