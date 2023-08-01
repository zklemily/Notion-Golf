package com.zkl.courseinfoservice.golfdata.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GolfCourse {
    @JsonProperty("_id")
    private String id;
    private String name;
    @JsonProperty("website")
    private String url;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String holes;
    private List<Scorecard> scorecard;

    public GolfCourse(String id, String name, String url, String address, String city, String state, String zip, String country, String holes, List<Scorecard> scorecard) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.holes = holes;
        this.scorecard = scorecard;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHoles() {
        return holes;
    }

    public void setHoles(String holes) {
        this.holes = holes;
    }

    public List<Scorecard> getScorecard() {
        return scorecard;
    }

    public void setScorecard(List<Scorecard> scorecard) {
        this.scorecard = scorecard;
    }
}
