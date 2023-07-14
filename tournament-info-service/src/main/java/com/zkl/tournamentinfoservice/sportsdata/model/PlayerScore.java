package com.zkl.tournamentinfoservice.sportsdata.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public class PlayerScore {
    @JsonProperty("PlayerID")
    private String id;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Rank")
    private Integer rank;
    @JsonProperty("TotalScore")
    private Double score;
    @JsonProperty("TotalStrokes")
    private Double strokes;
    @JsonProperty("TeeTime")
    private LocalDateTime teeTime;
    @JsonProperty("Rounds")
    private List<Round> rounds;
}
