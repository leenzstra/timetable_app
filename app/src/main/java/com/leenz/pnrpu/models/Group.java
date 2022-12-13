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
public class Group {
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("faculty")
    public String faculty;
    @JsonProperty("direction")
    public String direction;
    @JsonProperty("group_name")
    public String group_name;
}
