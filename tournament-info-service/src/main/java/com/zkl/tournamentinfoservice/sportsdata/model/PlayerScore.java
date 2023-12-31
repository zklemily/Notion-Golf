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
    @JsonProperty("Country")
    private String country;
    @JsonProperty("TotalScore")
    private Double score;
    @JsonProperty("TotalStrokes")
    private Double strokes;
    @JsonProperty("TeeTime")
    private LocalDateTime teeTime;
//    @JsonProperty("Rounds")
//    private List<Round> rounds;
    @JsonProperty("Eagles")
    private Double eagles;
    @JsonProperty("Birdies")
    private Double birdies;
    @JsonProperty("Pars")
    private Double pars;
    @JsonProperty("Bogeys")
    private Double bogeys;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getStrokes() {
        return strokes;
    }

    public void setStrokes(Double strokes) {
        this.strokes = strokes;
    }

    public LocalDateTime getTeeTime() {
        return teeTime;
    }

    public void setTeeTime(LocalDateTime teeTime) {
        this.teeTime = teeTime;
    }

    public Double getEagles() {
        return eagles;
    }

    public void setEagles(Double eagles) {
        this.eagles = eagles;
    }

    public Double getBirdies() {
        return birdies;
    }

    public void setBirdies(Double birdies) {
        this.birdies = birdies;
    }

    public Double getPars() {
        return pars;
    }

    public void setPars(Double pars) {
        this.pars = pars;
    }

    public Double getBogeys() {
        return bogeys;
    }

    public void setBogeys(Double bogeys) {
        this.bogeys = bogeys;
    }
}
