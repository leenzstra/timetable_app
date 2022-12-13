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
public class Day {
    @JsonProperty("id")
    private int id;

    @JsonProperty("group_id")
    private int groupId;

    @JsonProperty("day")
    private String dayName;

    @JsonProperty("week_num")
    private int weekNum;

    @JsonProperty("table")
    private Lesson[] lessons;

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
