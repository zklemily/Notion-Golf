package com.zkl.notionpageservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Comparator;
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
    @JsonProperty("Eagles")
    private Double eagles;
    @JsonProperty("Birdies")
    private Double birdies;
    @JsonProperty("Pars")
    private Double pars;
    @JsonProperty("Bogeys")
    private Double bogeys;

    public PlayerScore(String id, String name, Integer rank, String country, Double score, Double strokes, LocalDateTime teeTime, Double eagles, Double birdies, Double pars, Double bogeys) {
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.country = country;
        this.score = score;
        this.strokes = strokes;
        this.teeTime = teeTime;
        this.eagles = eagles;
        this.birdies = birdies;
        this.pars = pars;
        this.bogeys = bogeys;
    }

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

    @Override
    public String toString() {
        return "PlayerScore{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", rank=" + rank +
                ", country='" + country + '\'' +
                ", score=" + score +
                ", strokes=" + strokes +
                ", teeTime=" + teeTime +
                ", eagles=" + eagles +
                ", birdies=" + birdies +
                ", pars=" + pars +
                ", bogeys=" + bogeys +
                '}';
    }
}
