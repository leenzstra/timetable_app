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
public class Professor {
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("department")
    public String direction;
    @JsonProperty("position")
    public String position;
}