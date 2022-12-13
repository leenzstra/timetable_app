package com.leenz.pnrpu.models;

import android.annotation.SuppressLint;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lesson {
    @JsonProperty("time")
    private int time;

    @JsonProperty("subject_name")
    private String subjectName;

    @JsonProperty("subject_type")
    private String subjectType;

    @JsonProperty("teacher")
    private String teacherName;

    @JsonProperty("location")
    private String location;

    @SuppressLint("DefaultLocale")
    public String getTimeString(){
        int hours = time / 3600;
        int minutes = (time - hours * 3600) / 60;
        return String.format("%d:%02d", hours, minutes);
    }
}
