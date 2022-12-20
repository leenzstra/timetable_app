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
public class Response {
    @JsonProperty("result")
    private boolean result;

    @JsonProperty("message")
    private String message;
}
