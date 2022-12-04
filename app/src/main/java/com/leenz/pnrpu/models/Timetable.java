package com.leenz.pnrpu.models;

import com.leenz.pnrpu.models.Day;

public class Timetable {
    private final Day[] days;

    public Timetable(Day[] days) {
        this.days = days;
    }

    public Day[] getDays() {
        return days;
    }
}
