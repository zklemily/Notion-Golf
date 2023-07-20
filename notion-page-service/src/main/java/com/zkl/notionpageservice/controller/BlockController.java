package com.zkl.notionpageservice.controller;

import com.zkl.notionpageservice.notion.NotionClient;
import com.zkl.notionpageservice.notion.config.NotionConfigProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blocks")
public class BlockController {
    private final NotionClient client;
    private final NotionConfigProperties notionConfigProperties;

    public BlockController(NotionClient client, NotionConfigProperties notionConfigProperties) {
        this.client = client;
        this.notionConfigProperties = notionConfigProperties;
    }


}
