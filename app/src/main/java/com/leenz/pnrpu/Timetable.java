package com.leenz.pnrpu;

public class Timetable {
    private final Day[] days;

    public Timetable(Day[] days) {
        this.days = days;
    }

    public Day[] getDays() {
        return days;
    }
}
