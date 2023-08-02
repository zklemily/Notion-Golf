package com.zkl.courseinfoservice.golfdata.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zkl.courseinfoservice.golfdata.config.GolfDataConfig;
import com.zkl.courseinfoservice.golfdata.model.GolfCourse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;

@Service
public class GolfDataService {
    private final ObjectMapper objectMapper;
    private final GolfDataConfig golfDataConfig;

    public GolfDataService(ObjectMapper objectMapper, GolfDataConfig golfDataConfig) {
        this.objectMapper = objectMapper;
        this.golfDataConfig = golfDataConfig;
    }

    public List<GolfCourse> getGolfCourses(String name) throws IOException, InterruptedException {
        String url = golfDataConfig.getApiUrl() + name;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("X-RapidAPI-Key", golfDataConfig.getAuthToken())
                .header("X-RapidAPI-Host", golfDataConfig.getAuthHost())
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        int statusCode = response.statusCode();
        if (statusCode >= 200 && statusCode < 300) {
            JsonNode responseBodyJson = objectMapper.readTree(response.body());
            if (responseBodyJson.has("message") && responseBodyJson.get("message").asText().equals("No courses found")) {
                return Collections.emptyList();
            }
            return objectMapper.readValue(response.body(), new TypeReference<>() {});
        } else {
            throw new IOException("Error fetching golf courses. Status code: " + statusCode);
        }

    }
}
