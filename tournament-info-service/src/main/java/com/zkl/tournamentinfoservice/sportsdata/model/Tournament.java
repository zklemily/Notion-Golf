package com.zkl.tournamentinfoservice.sportsdata.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class Tournament {
    @JsonProperty("TournamentID")
    private String id;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("StartDate")
    private LocalDate startDate;
    @JsonProperty("EndDate")
    private LocalDate endDate;
    @JsonProperty("Venue")
    private String venue;
    @JsonProperty("Location")
    private String location;
    @JsonProperty("City")
    private String city;
    @JsonProperty("State")
    private String state;
    @JsonProperty("Country")
    private String country;
    @JsonProperty("IsInProgress")
    private Boolean inProgress;
    @JsonProperty("IsOver")
    private Boolean isOver;
    @JsonProperty("Par")
    private Double par;
    @JsonProperty("Yards")
    private Double yards;

    public Tournament(String id, String name, LocalDate startDate, LocalDate endDate, String venue, String location, String city, String state, String country, Boolean inProgress, Boolean isOver, Double par, Double yards) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.venue = venue;
        this.location = location;
        this.city = city;
        this.state = state;
        this.country = country;
        this.inProgress = inProgress;
        this.isOver = isOver;
        this.par = par;
        this.yards = yards;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean getInProgress() {
        return inProgress;
    }

    public void setInProgress(Boolean inProgress) {
        this.inProgress = inProgress;
    }

    public Boolean getOver() {
        return isOver;
    }

    public void setOver(Boolean over) {
        isOver = over;
    }

    public Double getPar() {
        return par;
    }

    public void setPar(Double par) {
        this.par = par;
    }

    public Double getYards() {
        return yards;
    }

    public void setYards(Double yards) {
        this.yards = yards;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", venue='" + venue + '\'' +
                ", location='" + location + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", inProgress=" + inProgress +
                ", isOver=" + isOver +
                ", par=" + par +
                ", yards=" + yards +
                '}';
    }
}
