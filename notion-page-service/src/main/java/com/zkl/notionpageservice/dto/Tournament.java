package com.zkl.notionpageservice.dto;

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
    @JsonProperty("City")
    private String city;
    @JsonProperty("State")
    private String state;
    @JsonProperty("Country")
    private String country;
    @JsonProperty("Par")
    private Double par;
    @JsonProperty("Yards")
    private Double yards;

    public Tournament() {
    }

    public Tournament(String name, LocalDate startDate, LocalDate endDate, String venue, String city, String state, String country, Double par, Double yards) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.venue = venue;
        this.city = city;
        this.state = state;
        this.country = country;
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
                "name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", venue='" + venue + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", par=" + par +
                ", yards=" + yards +
                '}';
    }
}
