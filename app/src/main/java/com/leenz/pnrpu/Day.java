package com.leenz.pnrpu;

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
}
