package com.leenz.pnrpu.models.timetablemodels;


public class Timetable {
    private final Day[] days;

    public Timetable(Day[] days) {
        this.days = days;
    }

    public Day[] getDays() {
        return days;
    }
}
