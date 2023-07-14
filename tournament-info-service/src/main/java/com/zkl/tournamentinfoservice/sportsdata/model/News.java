package com.zkl.tournamentinfoservice.sportsdata.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class News {
    @JsonProperty("NewsID")
    private String id;
    @JsonProperty("PlayerID")
    private String playerId;
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Content")
    private String content;
    @JsonProperty("Updated")
    private LocalDate date;
    @JsonProperty("OriginalSourceUrl")
    private String url;

    public News(String id, String playerId, String title, String content, LocalDate date, String url) {
        this.id = id;
        this.playerId = playerId;
        this.title = title;
        this.content = content;
        this.date = date;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
