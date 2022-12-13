package com.leenz.pnrpu.models;

public class Professor {
    private final int image;
    private final String name;
    private final String department;
    private final String position;

    public Professor(int img, String name, String department, String position) {
        this.image = img;
        this.name = name;
        this.department = department;
        this.position = position;
    }
    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() { return department; }
    public String getPosition() { return position; }

}
