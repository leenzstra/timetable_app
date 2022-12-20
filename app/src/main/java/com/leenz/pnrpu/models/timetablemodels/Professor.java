package com.leenz.pnrpu.models.timetablemodels;

import android.graphics.Bitmap;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Professor {
    @JsonProperty("id")
    private int id;
    @JsonProperty("image_url")
    private String image;
    @JsonProperty("name")
    private String name;
    @JsonProperty("department")
    private String department;
    @JsonProperty("position")
    private String position;
    private Bitmap imageBmp;

}
