package com.zkl.notionpageservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class Round {
    @JsonProperty("Hole 1")
    private int hole1;
    @JsonProperty("Hole 2")
    private int hole2;
    @JsonProperty("Hole 3")
    private int hole3;
    @JsonProperty("Hole 4")
    private int hole4;
    @JsonProperty("Hole 5")
    private int hole5;
    @JsonProperty("Hole 6")
    private int hole6;
    @JsonProperty("Hole 7")
    private int hole7;
    @JsonProperty("Hole 8")
    private int hole8;
    @JsonProperty("Hole 9")
    private int hole9;
    @JsonProperty("Hole 10")
    private int hole10;
    @JsonProperty("Hole 11")
    private int hole11;
    @JsonProperty("Hole 12")
    private int hole12;
    @JsonProperty("Hole 13")
    private int hole13;
    @JsonProperty("Hole 14")
    private int hole14;
    @JsonProperty("Hole 15")
    private int hole15;
    @JsonProperty("Hole 16")
    private int hole16;
    @JsonProperty("Hole 17")
    private int hole17;
    @JsonProperty("Hole 18")
    private int hole18;
    @JsonProperty("Golf Course")
    private String coursePageId;
    @JsonProperty("Tee")
    private String teeColor;
    @JsonProperty("Total Strokes")
    private int totalStrokes;
    @JsonProperty("Holes")
    private String holes;
    @JsonProperty("Par")
    private int par;
    @JsonProperty("Birdies")
    private int birdies;
    @JsonProperty("Bogies")
    private int bogies;

    @JsonProperty("Date")
    private LocalDate date;

    public Round() {
    }

    public Round(int hole1, int hole2, int hole3, int hole4, int hole5, int hole6, int hole7, int hole8, int hole9, int hole10, int hole11, int hole12, int hole13, int hole14, int hole15, int hole16, int hole17, int hole18, String coursePageId, String teeColor, int totalStrokes, String holes, int par, int birdies, int bogies, LocalDate date) {
        this.hole1 = hole1;
        this.hole2 = hole2;
        this.hole3 = hole3;
        this.hole4 = hole4;
        this.hole5 = hole5;
        this.hole6 = hole6;
        this.hole7 = hole7;
        this.hole8 = hole8;
        this.hole9 = hole9;
        this.hole10 = hole10;
        this.hole11 = hole11;
        this.hole12 = hole12;
        this.hole13 = hole13;
        this.hole14 = hole14;
        this.hole15 = hole15;
        this.hole16 = hole16;
        this.hole17 = hole17;
        this.hole18 = hole18;
        this.coursePageId = coursePageId;
        this.teeColor = teeColor;
        this.totalStrokes = totalStrokes;
        this.holes = holes;
        this.par = par;
        this.birdies = birdies;
        this.bogies = bogies;
        this.date = date;
    }

    public int getHole1() {
        return hole1;
    }

    public void setHole1(int hole1) {
        this.hole1 = hole1;
    }

    public int getHole2() {
        return hole2;
    }

    public void setHole2(int hole2) {
        this.hole2 = hole2;
    }

    public int getHole3() {
        return hole3;
    }

    public void setHole3(int hole3) {
        this.hole3 = hole3;
    }

    public int getHole4() {
        return hole4;
    }

    public void setHole4(int hole4) {
        this.hole4 = hole4;
    }

    public int getHole5() {
        return hole5;
    }

    public void setHole5(int hole5) {
        this.hole5 = hole5;
    }

    public int getHole6() {
        return hole6;
    }

    public void setHole6(int hole6) {
        this.hole6 = hole6;
    }

    public int getHole7() {
        return hole7;
    }

    public void setHole7(int hole7) {
        this.hole7 = hole7;
    }

    public int getHole8() {
        return hole8;
    }

    public void setHole8(int hole8) {
        this.hole8 = hole8;
    }

    public int getHole9() {
        return hole9;
    }

    public void setHole9(int hole9) {
        this.hole9 = hole9;
    }

    public int getHole10() {
        return hole10;
    }

    public void setHole10(int hole10) {
        this.hole10 = hole10;
    }

    public int getHole11() {
        return hole11;
    }

    public void setHole11(int hole11) {
        this.hole11 = hole11;
    }

    public int getHole12() {
        return hole12;
    }

    public void setHole12(int hole12) {
        this.hole12 = hole12;
    }

    public int getHole13() {
        return hole13;
    }

    public void setHole13(int hole13) {
        this.hole13 = hole13;
    }

    public int getHole14() {
        return hole14;
    }

    public void setHole14(int hole14) {
        this.hole14 = hole14;
    }

    public int getHole15() {
        return hole15;
    }

    public void setHole15(int hole15) {
        this.hole15 = hole15;
    }

    public int getHole16() {
        return hole16;
    }

    public void setHole16(int hole16) {
        this.hole16 = hole16;
    }

    public int getHole17() {
        return hole17;
    }

    public void setHole17(int hole17) {
        this.hole17 = hole17;
    }

    public int getHole18() {
        return hole18;
    }

    public void setHole18(int hole18) {
        this.hole18 = hole18;
    }

    public String getCoursePageId() {
        return coursePageId;
    }

    public void setCoursePageId(String coursePageId) {
        this.coursePageId = coursePageId;
    }

    public String getTeeColor() {
        return teeColor;
    }

    public void setTeeColor(String teeColor) {
        this.teeColor = teeColor;
    }

    public int getTotalStrokes() {
        return totalStrokes;
    }

    public void setTotalStrokes(int totalStrokes) {
        this.totalStrokes = totalStrokes;
    }

    public String getHoles() {
        return holes;
    }

    public void setHoles(String holes) {
        this.holes = holes;
    }

    public int getPar() {
        return par;
    }

    public void setPar(int par) {
        this.par = par;
    }

    public int getBirdies() {
        return birdies;
    }

    public void setBirdies(int birdies) {
        this.birdies = birdies;
    }

    public int getBogies() {
        return bogies;
    }

    public void setBogies(int bogies) {
        this.bogies = bogies;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
