package com.leenz.pnrpu.models.pagesmodels;

import androidx.annotation.Nullable;

public class TimetablepageModel {
    @Nullable
    public String groupName;

    @Nullable
    public String timetableTypeName;

    @Nullable
    public String pageHeader;

    @Nullable
    public String weekNumber;

    @Nullable
    public String todayString;

    @Nullable
    public String currentDate;

    @Nullable
    public String professorsLabel;

    @Nullable
    public String timetableLabel;

    @Nullable
    public String menuLabel;

    public TimetablepageModel(@Nullable String groupName, @Nullable String timetableTypeName, @Nullable String pageHeader, @Nullable String weekNumber, @Nullable String todayString, @Nullable String currentDate, @Nullable String professorsLabel, @Nullable String timetableLabel, @Nullable String menuLabel) {
        this.groupName = groupName;
        this.timetableTypeName = timetableTypeName;
        this.pageHeader = pageHeader;
        this.weekNumber = weekNumber;
        this.todayString = todayString;
        this.currentDate = currentDate;
        this.professorsLabel = professorsLabel;
        this.timetableLabel = timetableLabel;
        this.menuLabel = menuLabel;
    }
}
