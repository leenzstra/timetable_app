package com.leenz.pnrpu.models.timetablemodels;

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
public class Comment {
    @JsonProperty("mark")
    private int mark;

    @JsonProperty("comment")
    private String comment;
}
