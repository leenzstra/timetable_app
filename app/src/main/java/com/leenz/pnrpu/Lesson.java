package com.leenz.pnrpu;

public class Lesson {
    private final String time;
    private final String subjectName;
    private final String subjectType;
    private final String teacherName;
    private final String location;

    public Lesson(String time, String subjectName, String subjectType, String teacherName, String location) {
        this.time = time;
        this.subjectName = subjectName;
        this.subjectType = subjectType;
        this.teacherName = teacherName;
        this.location = location;
    }
    public String getTime() {
        return time;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getLocation() {
        return location;
    }
}
