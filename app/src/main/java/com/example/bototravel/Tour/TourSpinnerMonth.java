package com.example.bototravel.Tour;

public enum TourSpinnerMonth {
    SpinnerMode1("Tháng 1"),
    SpinnerMode2("Tháng 2"),
    SpinnerMode3("Tháng 3"),
    SpinnerMode4("Tháng 4"),
    SpinnerMode5("Tháng 5"),
    SpinnerMode6("Tháng 6"),
    SpinnerMode7("Tháng 7"),
    SpinnerMode8("Tháng 8"),
    SpinnerMode9("Tháng 9"),
    SpinnerMode10("Tháng 10"),
    SpinnerMode11("Tháng 11"),
    SpinnerMode12("Tháng 12");

    private String month;

    TourSpinnerMonth(String month) {
        this.month = month;
    }

    public String getMonth() {
        return month;
    }
}
