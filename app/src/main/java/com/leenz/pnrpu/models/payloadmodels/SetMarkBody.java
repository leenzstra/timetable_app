package com.leenz.pnrpu.models.payloadmodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SetMarkBody {
    @JsonProperty("sid")
    private int teacherId;

    @JsonProperty("mark")
    private int mark;

    @JsonProperty("comment")
    private String comment;
}
