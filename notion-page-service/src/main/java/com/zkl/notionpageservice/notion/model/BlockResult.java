package com.zkl.notionpageservice.notion.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class BlockResult {
    private String object;
    @JsonProperty("results")
    private List<Block> blocks = new ArrayList<>();
    @JsonProperty("next_cursor")
    private Boolean nextCursor;
    @JsonProperty("has_more")
    private Boolean hasMore;

    public BlockResult(String object, List<Block> blocks, Boolean nextCursor, Boolean hasMore) {
        this.object = object;
        this.blocks = blocks;
        this.nextCursor = nextCursor;
        this.hasMore = hasMore;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    public Boolean getNextCursor() {
        return nextCursor;
    }

    public void setNextCursor(Boolean nextCursor) {
        this.nextCursor = nextCursor;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }
}
