package com.zkl.tournamentinfoservice.sportsdata.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class Player {
    @JsonProperty("PlayerID")
    private String id;
    @JsonProperty("FirstName")
    private String firstName;
    @JsonProperty("LastName")
    private String lastName;
    @JsonProperty("BirthDate")
    private LocalDate birthdate;
    @JsonProperty("BirthCity")
    private String birthCity;
    @JsonProperty("BirthState")
    private String birthState;
    @JsonProperty("Country")
    private String country;
    @JsonProperty("PhotoUrl")
    private String photoUrl;

    public Player(String id, String firstName, String lastName, LocalDate birthdate, String birthCity, String birthState, String country, String photoUrl) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.birthCity = birthCity;
        this.birthState = birthState;
        this.country = country;
        this.photoUrl = photoUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getBirthCity() {
        return birthCity;
    }

    public void setBirthCity(String birthCity) {
        this.birthCity = birthCity;
    }

    public String getBirthState() {
        return birthState;
    }

    public void setBirthState(String birthState) {
        this.birthState = birthState;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
