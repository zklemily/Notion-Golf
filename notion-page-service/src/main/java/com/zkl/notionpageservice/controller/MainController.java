package com.zkl.notionpageservice.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zkl.notionpageservice.dto.News;
import com.zkl.notionpageservice.notion.NotionClient;
import com.zkl.notionpageservice.notion.config.NotionConfigProperties;
import com.zkl.notionpageservice.dto.Tournament;
import com.zkl.notionpageservice.notion.service.JsonService;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {
    private final NotionClient client;
    private final NotionConfigProperties notionConfigProperties;
    private final ObjectMapper objectMapper;
    private final JsonService jsonService;

    public MainController(NotionClient client, NotionConfigProperties notionConfigProperties, ObjectMapper objectMapper, JsonService jsonService) {
        this.client = client;
        this.notionConfigProperties = notionConfigProperties;
        this.objectMapper = objectMapper;
        this.jsonService = jsonService;
    }

    @GetMapping("/update-news")
    public ResponseEntity<String> updateNews() throws IOException, InterruptedException, JSONException {
        String getNewsUrl = "http://localhost:8081/news";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(getNewsUrl))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        String titleBlockId = "7dfdd5d726274eb88467808f433ab74f";
        String contentBlockId = "9aa509b143924dffa276a47da94eb88d";
        List<News> news = objectMapper.readValue(response.body(), new TypeReference<>() {});
        if (!news.isEmpty()) {
            News today = news.get(0);
            HttpResponse<String> titleResponse = client.databases.updateBlock(titleBlockId, jsonService.createBlockJsonPayload(today.getTitle()));
            HttpResponse<String> contentResponse = client.databases.updateBlock(contentBlockId, jsonService.createBlockJsonPayload(today.getContent()));

            HttpHeaders headers = new HttpHeaders();
            response.headers().map().forEach(headers::addAll);

            return ResponseEntity.status(titleResponse.statusCode())
                    .headers(headers)
                    .body(response.body());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/update-tournaments")
    public ResponseEntity<String> updateTournaments() throws IOException, InterruptedException, JSONException {
        String getNewsUrl = "http://localhost:8081/tournaments";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(getNewsUrl))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        String databaseId = "e9cfc0867f74445c8948abfcfe6c2bce";

        List<Tournament> tournaments = objectMapper.readValue(response.body(),  new TypeReference<>() {});
        System.out.println("Total number: " + tournaments.size());
        if (!tournaments.isEmpty()) {
            for (int i = 0; i < tournaments.size(); i++) {
                System.out.println("Current index: " + i + " Current tournament: " + tournaments.get(i).getName());
                String tournamentJson = jsonService.createTournamentJsonPayload(databaseId, tournaments.get(i));
                client.databases.createPageInDatabase(tournamentJson);
            }
            return ResponseEntity.ok("Tournaments added.");
        }

        return ResponseEntity.notFound().build();
    }
}
