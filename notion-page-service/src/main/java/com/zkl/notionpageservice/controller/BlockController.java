package com.zkl.notionpageservice.controller;

import com.zkl.notionpageservice.notion.NotionClient;
import com.zkl.notionpageservice.notion.config.NotionConfigProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/blocks")
public class BlockController {
    private final NotionClient client;
    private final NotionConfigProperties notionConfigProperties;

    public BlockController(NotionClient client, NotionConfigProperties notionConfigProperties) {
        this.client = client;
        this.notionConfigProperties = notionConfigProperties;
    }

    @GetMapping("/{blockId}")
    public ResponseEntity<String> getBlockChildren() throws IOException, InterruptedException {
        HttpResponse<String> response = client.databases.getBlockChildren("test");
        HttpHeaders headers = new HttpHeaders();
        response.headers().map().forEach(headers::addAll);

        // Return the response entity with headers, body, and status code
        return ResponseEntity.status(response.statusCode())
                .headers(headers)
                .body(response.body());
    }


}
