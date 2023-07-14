package com.zkl.notionpageservice.controller;

import com.squareup.okhttp.Response;
import com.zkl.notionpageservice.notion.NotionClient;
import com.zkl.notionpageservice.notion.config.NotionConfigProperties;
import com.zkl.notionpageservice.notion.model.Page;
import com.zkl.notionpageservice.notion.model.Tournament;
import com.zkl.notionpageservice.service.TournamentsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/database")
public class InfoController {

    private final NotionClient client;
    private final NotionConfigProperties notionConfigProperties;

    public InfoController(NotionClient client, NotionConfigProperties notionConfigProperties) {
        this.client = client;
        this.notionConfigProperties = notionConfigProperties;
    }

    @GetMapping
    public List<Tournament> findAll() {
        List<Page> pages = client.databases.query(notionConfigProperties.databaseId());
        return pages.stream().map(TournamentsService::mapPageToTournament).toList();
    }

    @PostMapping
    public ResponseEntity<String> createPage() throws IOException {
        Response response = client.databases.create(notionConfigProperties.databaseId());
        System.out.println(response.body().string());
        return ResponseEntity.ok("Page is created");
    }
}
