package com.example.master_thesis.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
public class BoxscoreDto {
    @JsonProperty("Live")
    private boolean live;

    @JsonProperty("Referees")
    private String referees;

    @JsonProperty("Attendance")
    private int attendance;

    @JsonProperty("ByQuarter")
    private List<QuarterScoreDto> byQuarter;

    @JsonProperty("EndOfQuarter")
    private List<QuarterScoreDto> endOfQuarter;

    @JsonProperty("Stats")
    private List<TeamStatisticsDto> statistics;
}