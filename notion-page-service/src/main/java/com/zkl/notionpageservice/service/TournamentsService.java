package com.zkl.notionpageservice.service;

import com.zkl.notionpageservice.notion.model.Page;
import com.zkl.notionpageservice.dto.Tournament;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TournamentsService {

    public static Tournament mapPageToTournament(Page page) {

        return new Tournament(
                page.getProperties().get("ID").get("rich_text").get(0).get("text").get("content").asText(),
                page.getProperties().get("Name").get("title").get(0).get("text").get("content").asText(),
                LocalDate.parse(page.getProperties().get("Date").get("date").get("start").asText()),
                LocalDate.parse(page.getProperties().get("Date").get("date").get("end").asText()),
                page.getProperties().get("Venue").get("rich_text").get(0).get("text").get("content").asText(),
                page.getProperties().get("City").get("rich_text").get(0).get("text").get("content").asText(),
                page.getProperties().get("State").get("select").get("name").asText(),
                page.getProperties().get("Country").get("select").get("name").asText(),
                page.getProperties().get("Par").get("number").asDouble(),
                page.getProperties().get("Yards").get("number").asDouble()
        );
    }
}
