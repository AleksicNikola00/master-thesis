package com.example.master_thesis.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class QuarterScoreDto {
    @JsonProperty("Team")
    private String team;

    @JsonProperty("Quarter1")
    private int quarter1;

    @JsonProperty("Quarter2")
    private int quarter2;

    @JsonProperty("Quarter3")
    private int quarter3;

    @JsonProperty("Quarter4")
    private int quarter4;
}