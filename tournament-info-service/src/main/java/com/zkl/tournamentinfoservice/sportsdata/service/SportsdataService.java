package com.zkl.tournamentinfoservice.sportsdata.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zkl.tournamentinfoservice.sportsdata.model.LeaderboardResponse;
import com.zkl.tournamentinfoservice.sportsdata.model.PlayerScore;
import com.zkl.tournamentinfoservice.sportsdata.model.Tournament;
import com.zkl.tournamentinfoservice.sportsdata.config.SportsdataConfig;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class SportsdataService {
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final SportsdataConfig sportsdataConfig;

    public SportsdataService(HttpClient httpClient, ObjectMapper objectMapper, SportsdataConfig sportsdataConfig) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
        this.sportsdataConfig = sportsdataConfig;
    }

    public List<Tournament> getTournaments() throws IOException, InterruptedException {
        String url = sportsdataConfig.getApiUrl() + "Tournaments?key=" + sportsdataConfig.getAuthToken();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        List<Tournament> tournaments = objectMapper.readValue(response.body(), new TypeReference<>() {
        });
        return tournaments;
    }

    public List<PlayerScore> getLeaderboard(String tournamentId) throws IOException, InterruptedException {
        String url = sportsdataConfig.getApiUrl() + "Leaderboard/" + tournamentId + "?key=" + sportsdataConfig.getAuthToken();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        LeaderboardResponse leaderboardResponse = objectMapper.readValue(response.body(), LeaderboardResponse.class);
        return leaderboardResponse.getPlayerScores();
    }
}
