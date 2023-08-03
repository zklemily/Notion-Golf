package com.zkl.notionpageservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Scorecard {
    @JsonProperty("Hole")
    private int hole;
    @JsonProperty("Par")
    private int par;
    private Map<String, TeeBox> tees;
    @JsonProperty("Handicap")
    private int handicap;

    public Scorecard(int hole, int par, Map<String, TeeBox> tees, int handicap) {
        this.hole = hole;
        this.par = par;
        this.tees = tees;
        this.handicap = handicap;
    }

    public int getHole() {
        return hole;
    }

    public void setHole(int hole) {
        this.hole = hole;
    }

    public int getPar() {
        return par;
    }

    public void setPar(int par) {
        this.par = par;
    }

    public Map<String, TeeBox> getTees() {
        return tees;
    }

    public void setTees(Map<String, TeeBox> tees) {
        this.tees = tees;
    }

    public int getHandicap() {
        return handicap;
    }

    public void setHandicap(int handicap) {
        this.handicap = handicap;
    }
}
