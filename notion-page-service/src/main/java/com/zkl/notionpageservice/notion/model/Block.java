package com.zkl.notionpageservice.notion.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Block {
    private String object;
    private String id;
    @JsonProperty("created_time")
    private LocalDateTime createdTime;
    @JsonProperty("last_edited_time")
    private LocalDateTime lastEditedTime;
    private String type;
    private JsonNode paragraph;

    public Block(String object, String id, LocalDateTime createdTime, LocalDateTime lastEditedTime, String type, JsonNode paragraph) {
        this.object = object;
        this.id = id;
        this.createdTime = createdTime;
        this.lastEditedTime = lastEditedTime;
        this.type = type;
        this.paragraph = paragraph;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getLastEditedTime() {
        return lastEditedTime;
    }

    public void setLastEditedTime(LocalDateTime lastEditedTime) {
        this.lastEditedTime = lastEditedTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JsonNode getParagraph() {
        return paragraph;
    }

    public void setParagraph(JsonNode paragraph) {
        this.paragraph = paragraph;
    }
}
