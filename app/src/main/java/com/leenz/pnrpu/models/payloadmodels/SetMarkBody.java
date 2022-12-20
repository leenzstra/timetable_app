package com.leenz.pnrpu.models.payloadmodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SetMarkBody {
    @JsonProperty("teacher_id")
    private int teacherId;

    @JsonProperty("mark")
    private int mark;

    @JsonProperty("comment")
    private String comment;
}
