package com.leenz.pnrpu;

public class Day {
    private int id;
    private int groupId;
    private String dayName;
    private int weekNum;
    private Lesson[] lessons;

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
}
