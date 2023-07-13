package com.zkl.notionpageservice.notion.service;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zkl.notionpageservice.notion.config.NotionConfigProperties;
import com.zkl.notionpageservice.notion.model.Database;
import com.zkl.notionpageservice.notion.model.Page;
import com.zkl.notionpageservice.notion.model.Tournament;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import java.io.IOException;
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

    public List<Page> query(String databaseId) {

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

    public Response create(String databaseId) throws IOException {
        String url = notionConfigProps.apiUrl() + "/v1/pages";
        log.info("Querying Notion database: {}", url);

        OkHttpClient client = new OkHttpClient();
        com.squareup.okhttp.MediaType mediaType = com.squareup.okhttp.MediaType.parse("application/json");
        com.squareup.okhttp.RequestBody body = com.squareup.okhttp.RequestBody.create(mediaType,
                "{\n  \"parent\": {\n    \"database_id\": \"" + databaseId + "\"\n  },\n  \"properties\": {\n    \"Name\": {\n      \"title\": [\n        {\n          \"text\": {\n            \"content\": \"KMPG Championship\"\n          }\n        }\n      ]\n    },\n    \"Country\": {\n      \"type\": \"select\",\n      \"select\": {\n        \"name\": \"USA\"\n      }\n    },\n    \"Is Over\": {\n      \"checkbox\": true\n    },\n    \"City\": {\n      \"rich_text\": [\n        {\n          \"text\": {\n            \"content\": \"New Jersey\"\n          }\n        }\n      ]\n    },\n    \"Venue\": {\n      \"rich_text\": [\n        {\n          \"text\": {\n            \"content\": \"NJ Country Club\"\n          }\n        }\n      ]\n    },\n    \"State\": {\n      \"select\": {\n        \"name\": \"NJ\"\n      }\n    },\n    \"Date\": {\n      \"date\": {\n        \"start\": \"2023-07-01\",\n        \"end\": \"2023-07-04\"\n      }\n    },\n    \"In Progress\": {\n      \"checkbox\": false\n    }\n  }\n}");
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Notion-Version", "2022-06-28")
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/json")
                .addHeader("Authorization", notionConfigProps.authToken())
                .build();
        Response response = client.newCall(request).execute();
        return response;
    }


}
