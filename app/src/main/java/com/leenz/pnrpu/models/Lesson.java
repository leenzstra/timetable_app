package com.leenz.pnrpu.models;

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
    private String time;

    @JsonProperty("subject_name")
    private String subjectName;

    @JsonProperty("subject_type")
    private String subjectType;

    @JsonProperty("teacher")
    private String teacherName;

    @JsonProperty("location")
    private String location;

}
