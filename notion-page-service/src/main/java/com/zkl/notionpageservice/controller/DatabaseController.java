package com.zkl.notionpageservice.controller;

import com.zkl.notionpageservice.notion.NotionClient;
import com.zkl.notionpageservice.notion.config.NotionConfigProperties;
import com.zkl.notionpageservice.notion.model.Page;
import com.zkl.notionpageservice.dto.Tournament;
import com.zkl.notionpageservice.service.TournamentsService;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/database")
public class DatabaseController {

    private final NotionClient client;
    private final NotionConfigProperties notionConfigProperties;

    public DatabaseController(NotionClient client, NotionConfigProperties notionConfigProperties) {
        this.client = client;
        this.notionConfigProperties = notionConfigProperties;
    }

    @GetMapping
    public List<Tournament> findAll() {
        List<Page> pages = client.databases.getDatabase(notionConfigProperties.databaseId());
        return pages.stream().map(TournamentsService::mapPageToTournament).toList();
    }

    @PostMapping("/create-page")
    public ResponseEntity<String> createPage() throws IOException, InterruptedException {
        HttpResponse<String> response = client.databases.createPageInDatabase("test");
        HttpHeaders headers = new HttpHeaders();
        response.headers().map().forEach(headers::addAll);

        // Return the response entity with headers, body, and status code
        return ResponseEntity.status(response.statusCode())
                .headers(headers)
                .body(response.body());
    }

    @PatchMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateBlock() throws IOException, InterruptedException, JSONException {
        String id = "9aa509b143924dffa276a47da94eb88d";
        HttpResponse<String> response = client.databases.updateBlock(id, "change content for this block");
        HttpHeaders headers = new HttpHeaders();
        response.headers().map().forEach(headers::addAll);

        // Return the response entity with headers, body, and status code
        return ResponseEntity.status(response.statusCode())
                .headers(headers)
                .body(response.body());
    }

    @PostMapping("/create-database")
    public ResponseEntity<String> createDatabase() {
        return null;
    }

}
