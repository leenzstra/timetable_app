package com.leenz.pnrpu.models;

public class Day {
    private final int id;
    private final int groupId;
    private final String dayName;
    private final int weekNum;
    private final Lesson[] lessons;

    public Day(int id, int groupId, String dayName, int weekNum, Lesson[] lessons) {
        this.id = id;
        this.groupId = groupId;
        this.dayName = dayName;
        this.weekNum = weekNum;
        this.lessons = lessons;
    }

    public String getDayName() {
        return dayName;
    }

    public int getWeekNum() {
        return weekNum;
    }

    public Lesson[] getLessons() {
        return lessons;
    }

    public int getId() {
        return id;
    }

    public int getGroupId() {
        return groupId;
    }

    public int getDayOfWeekNumber(){
        int curDayOfWeek = 0;
        switch (dayName) {
            case "ПОНЕДЕЛЬНИК":
                curDayOfWeek = 2;
                break;
            case "ВТОРНИК":
                curDayOfWeek = 3;
                break;
            case "СРЕДА":
                curDayOfWeek = 4;
                break;
            case "ЧЕТВЕРГ":
                curDayOfWeek = 5;
                break;
            case "ПЯТНИЦА":
                curDayOfWeek = 6;
                break;
            case "СУББОТА":
                curDayOfWeek = 7;
                break;
        }
        return curDayOfWeek;
    }
}
