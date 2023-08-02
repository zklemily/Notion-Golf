package com.zkl.notionpageservice.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zkl.notionpageservice.dto.*;
import com.zkl.notionpageservice.notion.NotionClient;
import com.zkl.notionpageservice.notion.config.NotionConfigProperties;
import com.zkl.notionpageservice.notion.model.Block;
import com.zkl.notionpageservice.notion.model.BlockResult;
import com.zkl.notionpageservice.notion.model.Database;
import com.zkl.notionpageservice.notion.model.Page;
import com.zkl.notionpageservice.notion.service.JsonService;
import com.zkl.notionpageservice.service.TournamentsService;
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
import java.time.LocalDate;
import java.util.*;
import java.util.regex.PatternSyntaxException;

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
            HttpResponse<String> titleResponse = client.databases.updateBlock(titleBlockId, jsonService.createHeaderBlockJsonPayload(today.getTitle()));
            HttpResponse<String> contentResponse = client.databases.updateBlock(contentBlockId,
                    jsonService.createBlockJsonPayload(today.getContent() + "\nDate: " + today.getDate() + "\nSource: " + today.getUrl()));

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
        String getTournamentsUrl = "http://localhost:8081/tournaments";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(getTournamentsUrl))
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

    @GetMapping("/update-leaderboard")
    public ResponseEntity<String> updateLeaderboard() throws IOException, InterruptedException, JSONException {
        String getCurTournamentsUrl = "http://localhost:8081/tournaments/current";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(getCurTournamentsUrl))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        List<Tournament> curTournaments = objectMapper.readValue(response.body(),  new TypeReference<>() {});
        if (curTournaments.isEmpty()) {
            return ResponseEntity.ok("No current tournaments.");
        }

        List<Page> pages = client.databases.getDatabase(notionConfigProperties.databaseId());
        List<Tournament> databaseTournaments = pages.stream().map(TournamentsService::mapPageToTournament).toList();

        Map<String, String> tourIdToPageId = new HashMap<>();
        for (Tournament tournament : databaseTournaments) {
            for (Page page : pages) {
                String id = page.getProperties().get("ID").get("rich_text").get(0).get("text").get("content").asText();
                if (id.equals(tournament.getId())) {
                    tourIdToPageId.put(tournament.getId(), page.getId());
                }
            }
        }

        for (Tournament tournament : curTournaments) {
            if (tourIdToPageId.containsKey(tournament.getId())) {
                String getLeaderboardUrl = "http://localhost:8081/tournaments/" + tournament.getId();
                request = HttpRequest.newBuilder()
                        .uri(URI.create(getLeaderboardUrl))
                        .header("Content-Type", "application/json")
                        .GET()
                        .build();

                httpClient = HttpClient.newHttpClient();
                response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                List<PlayerScore> playerScores = objectMapper.readValue(response.body(),  new TypeReference<>() {});
                if (playerScores.isEmpty()) {
                    continue;
                }

                playerScores.sort(Comparator.comparing(PlayerScore::getRank).reversed());

                // test: change the data
                playerScores.get(0).setBirdies(10.0);

                String pageId = tourIdToPageId.get(tournament.getId());

                String databaseId = null;
                // check if the page has database
                HttpResponse<String> blockResponse = client.databases.getBlockChildren(pageId);
                BlockResult blockResult = objectMapper.readValue(blockResponse.body(), BlockResult.class);
                List<Block> blocks = blockResult.getBlocks();
                for (Block block : blocks) {
                    if (block.getType().equals("database")) {
                        databaseId = block.getId();
                    }
                }

                if (databaseId == null) {
                    // if no database, create one
                    String playerScoreJson = jsonService.createPlayerScoreJsonPayload(pageId);
                    HttpResponse<String> createDatabaseResponse = client.databases.createDatabase(playerScoreJson);
                    Database database = objectMapper.readValue(createDatabaseResponse.body(), Database.class);
                    databaseId = database.getId();
                    // insert data into database
                    for (PlayerScore playerScore : playerScores) {
                        playerScoreJson = jsonService.insertPlayerScoreJsonPayload(databaseId, playerScore);
                        client.databases.createPageInDatabase(playerScoreJson);
                    }
                } else {
                    // update data directly
                    // first need to fetch every row ("page") to get the page id in database, then update
                    Map<String, String> playerIdToPageId = new HashMap<>();
                    List<Page> curPages = client.databases.getDatabase(databaseId);
                    for (Page curPage : curPages) {
                        String curPlayerId = curPage.getProperties().get("Player ID").get("rich_text").get(0).get("text").get("content").asText();
                        for (PlayerScore playerScore : playerScores) {
                            if (playerScore.getId().equals(curPlayerId)) {
                                playerIdToPageId.put(curPlayerId, curPage.getId());
                            }
                        }
                    }
                    // if the current player is already in database, update
                    for (PlayerScore playerScore : playerScores) {
                        if (playerIdToPageId.containsKey(playerScore.getId())) {
                            String playerScoreJson = jsonService.updatePlayerScoreJsonPayload(playerScore);
                            client.databases.updatePageInDatabase(playerIdToPageId.get(playerScore.getId()), playerScoreJson);
                        } else {
                            // otherwise, insert
                            String playerScoreJson = jsonService.insertPlayerScoreJsonPayload(playerScore.getId(), playerScore);
                            client.databases.createPageInDatabase(playerScoreJson);
                        }
                    }
                }
            }
        }

        return ResponseEntity.ok("Leaderboard is updated");

    }

    @GetMapping("/get-fav-players")
    public List<String> getFavPlayers() throws IOException, InterruptedException {
        String blockId = "ebd7540c-244a-46b4-9067-8239cc365501";
        HttpResponse<String> blockResponse = client.databases.getBlock(blockId);
        Block block = objectMapper.readValue(blockResponse.body(), Block.class);
        String players = block.getParagraph().get("rich_text").get(0).get("text").get("content").asText().replace("Players:", "").trim();;
        try {
            return Arrays.asList(players.split(",\\s*"));
        } catch (PatternSyntaxException e) {
            System.out.println("Error: Invalid input format.");
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/get-course-name")
    public String getCourseName() throws IOException, InterruptedException {
        String blockId = "0055469241594427a3095ec7c687501c";
        HttpResponse<String> blockResponse = client.databases.getBlock(blockId);
        Block block = objectMapper.readValue(blockResponse.body(), Block.class);
        return block.getParagraph().get("rich_text").get(0).get("text").get("content").asText();
    }

    @GetMapping("/update-course")
    public ResponseEntity<String> updateCourse() throws IOException, InterruptedException, JSONException {
        String databaseId = "ee582466445047cf8a35a495e3f273c2";

        String getCourseUrl = "http://localhost:8082/golf-course/{" + getCourseName() + "}";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(getCourseUrl))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        List<GolfCourse> courses = objectMapper.readValue(response.body(), new TypeReference<>() {});

        if (courses.isEmpty()) {
            return ResponseEntity.ok("No courses found.");
        }

        for (GolfCourse golfCourse : courses) {
            // insert golf course as page in database
            String courseJson = jsonService.insertGolfCourseJsonPayload(databaseId, golfCourse);
            HttpResponse<String> createPageResponse = client.databases.createPageInDatabase(courseJson);

            // insert scorecard into page
            Page page = objectMapper.readValue(createPageResponse.body(), Page.class);
            String pageId = page.getId();
            List<Scorecard> scorecards = golfCourse.getScorecard();
        }
        return ResponseEntity.ok("Courses are added to database.");
    }
}
