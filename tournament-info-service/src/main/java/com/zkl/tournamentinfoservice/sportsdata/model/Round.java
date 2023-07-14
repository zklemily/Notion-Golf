package com.zkl.tournamentinfoservice.sportsdata.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class Round {
    @JsonProperty("PlayerRoundID")
    private String id;
    @JsonProperty("Day")
    private LocalDate date;
}
