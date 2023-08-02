package com.zkl.notionpageservice.dto;

public class TeeBox {
    private String color;
    private int yards;

    public TeeBox(String color, int yards) {
        this.color = color;
        this.yards = yards;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYards() {
        return yards;
    }

    public void setYards(int yards) {
        this.yards = yards;
    }
}
