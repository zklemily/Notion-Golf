package com.zkl.notionpageservice.notion.service;

import com.zkl.notionpageservice.notion.config.NotionConfigProperties;
import com.zkl.notionpageservice.notion.model.Database;
import com.zkl.notionpageservice.notion.model.Page;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Component
public class DatabaseService {

    private final Logger log = LoggerFactory.getLogger(DatabaseService.class);
    private final NotionConfigProperties notionConfigProps;
    private final RestTemplate restTemplate;

    public DatabaseService(NotionConfigProperties notionConfigProps, RestTemplate restTemplate) {
        this.notionConfigProps = notionConfigProps;
        this.restTemplate = restTemplate;
    }

    public HttpResponse<String> createDatabase(String newContent) throws IOException, InterruptedException {
        String url = notionConfigProps.apiUrl() + "/v1/databases/";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("accept", "application/json")
                .header("Notion-Version", "2022-06-28")
                .header("Content-Type", "application/json")
                .header("Authorization", notionConfigProps.authToken())
                .method("POST", HttpRequest.BodyPublishers.ofString(newContent))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return response;
    }

    public List<Page> getDatabase(String databaseId) {

        String url = notionConfigProps.apiUrl() + "/v1/databases/" + databaseId + "/query";
        log.info("Querying Notion database: {}", url);
        ResponseEntity<Database> db = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(getDefaultHeaders()),
                Database.class
        );

        return db.getBody().getPages();

    }

    private HttpHeaders getDefaultHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Notion-Version", notionConfigProps.apiVersion());
        headers.set("Authorization", notionConfigProps.authToken());
        return headers;
    }

    public HttpResponse<String> createPageInDatabase(String newContent) throws IOException, InterruptedException {
        String url = notionConfigProps.apiUrl() + "/v1/pages";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("accept", "application/json")
                .header("Notion-Version", "2022-06-28")
                .header("Content-Type", "application/json")
                .header("Authorization", notionConfigProps.authToken())
                .method("POST", HttpRequest.BodyPublishers.ofString(newContent))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.body());
        return response;
    }

    public HttpResponse<String> updatePageInDatabase(String pageId, String newContent) throws IOException, InterruptedException {
        String url = notionConfigProps.apiUrl() + "/v1/pages/" + pageId;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("accept", "application/json")
                .header("Notion-Version", "2022-06-28")
                .header("Content-Type", "application/json")
                .header("Authorization", notionConfigProps.authToken())
                .method("PATCH", HttpRequest.BodyPublishers.ofString(newContent))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }

    public HttpResponse<String> updateBlock(String blockId, String newContent) throws IOException, InterruptedException {
        String url = notionConfigProps.apiUrl() + "/v1/blocks/" + blockId;
        log.info("Querying Notion database: {}", url);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("accept", "application/json")
                .header("Notion-Version", "2022-06-28")
                .header("Content-Type", "application/json")
                .header("Authorization", notionConfigProps.authToken())
                .method("PATCH", HttpRequest.BodyPublishers.ofString(newContent))
                .build();


        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }

    public HttpResponse<String> getBlockChildren(String blockId) throws IOException, InterruptedException {
        String url = notionConfigProps.apiUrl() + "/v1/blocks/" + blockId + "/children?page_size=100";
        log.info("Querying Notion database: {}", url);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("accept", "application/json")
                .header("Notion-Version", "2022-06-28")
                .header("Content-Type", "application/json")
                .header("Authorization", notionConfigProps.authToken())
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();


        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }

    public HttpResponse<String> getBlock(String blockId) throws IOException, InterruptedException {
        String url = notionConfigProps.apiUrl() + "/v1/blocks/" + blockId;
        log.info("Querying Notion database: {}", url);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("accept", "application/json")
                .header("Notion-Version", "2022-06-28")
                .header("Content-Type", "application/json")
                .header("Authorization", notionConfigProps.authToken())
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();


        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }

    public HttpResponse<String> createBlock(String parentId, String content) throws IOException, InterruptedException {
        String url = notionConfigProps.apiUrl() + "/v1/blocks/" + parentId + "/children";

        log.info("Querying Notion database: {}", url);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("accept", "application/json")
                .header("Notion-Version", "2022-06-28")
                .header("Content-Type", "application/json")
                .header("Authorization", notionConfigProps.authToken())
                .method("PATCH", HttpRequest.BodyPublishers.ofString(content))
                .build();


        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Here: ");
        System.out.println(response.body());
        return response;
    }
}
