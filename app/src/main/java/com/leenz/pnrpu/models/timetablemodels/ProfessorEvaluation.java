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
public class ProfessorEvaluation {
    @JsonProperty("average_mark")
    private double averageMark;

    @JsonProperty("count")
    private double markCount;

    @JsonProperty("comments")
    private Comment[] comments;
}
