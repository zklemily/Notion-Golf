package com.zkl.notionpageservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.zkl.notionpageservice.dto.Round;
import com.zkl.notionpageservice.notion.model.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RoundService {

    public static Round mapPageToRound(Page page) {
        JsonNode properties = page.getProperties();
        return new Round(
                properties.get("Hole 1").get("number").asInt(),
                properties.get("Hole 2").get("number").asInt(),
                properties.get("Hole 3").get("number").asInt(),
                properties.get("Hole 4").get("number").asInt(),
                properties.get("Hole 5").get("number").asInt(),
                properties.get("Hole 6").get("number").asInt(),
                properties.get("Hole 7").get("number").asInt(),
                properties.get("Hole 8").get("number").asInt(),
                properties.get("Hole 9").get("number").asInt(),
                properties.get("Hole 10").get("number").asInt(),
                properties.get("Hole 11").get("number").asInt(),
                properties.get("Hole 12").get("number").asInt(),
                properties.get("Hole 13").get("number").asInt(),
                properties.get("Hole 14").get("number").asInt(),
                properties.get("Hole 15").get("number").asInt(),
                properties.get("Hole 16").get("number").asInt(),
                properties.get("Hole 17").get("number").asInt(),
                properties.get("Hole 18").get("number").asInt(),
                properties.get("Golf Course").get("relation").get(0).get("id").asText(),
                properties.get("Tee").get("select").get("name").asText(),
                properties.get("Total Strokes").get("formula").get("number").asInt(),
                properties.get("Holes").get("select").get("name").asText(),
                properties.get("Pars").get("number").asInt(),
                properties.get("Birdies").get("number").asInt(),
                properties.get("Bogies").get("number").asInt(),
                LocalDate.parse(properties.get("Date").get("date").get("start").asText())
        );
    }
}
