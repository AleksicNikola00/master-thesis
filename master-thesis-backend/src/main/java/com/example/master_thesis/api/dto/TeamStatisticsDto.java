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
public class TeamStatisticsDto {
    @JsonProperty("Team")
    private String team;

    @JsonProperty("Coach")
    private String coach;

    @JsonProperty("PlayersStats")
    private List<PlayerStatisticsDto> playersStatistics;
}