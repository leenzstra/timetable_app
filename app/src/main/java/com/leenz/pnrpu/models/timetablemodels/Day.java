package com.leenz.pnrpu.models.timetablemodels;

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
    private int day;

    @JsonProperty("week_num")
    private int weekNum;

    @JsonProperty("table")
    private Lesson[] lessons;

    public int getDayOfWeekNumber(){
        return day;
    }

    public String getDayName(){
        String curDayOfWeek = "";
        switch (day) {
            case 1:
                curDayOfWeek = "ПОНЕДЕЛЬНИК";
                break;
            case 2:
                curDayOfWeek = "ВТОРНИК";
                break;
            case 3:
                curDayOfWeek = "СРЕДА";
                break;
            case 4:
                curDayOfWeek = "ЧЕТВЕРГ";
                break;
            case 5:
                curDayOfWeek = "ПЯТНИЦА";
                break;
            case 6:
                curDayOfWeek = "СУББОТА";
                break;
        }
        return curDayOfWeek;
    }
}
