package com.zkl.notionpageservice.notion;

import com.zkl.notionpageservice.notion.service.DatabaseService;
import org.springframework.stereotype.Component;

@Component
public class NotionClient {

    public final DatabaseService databases;

    public NotionClient(DatabaseService databases) {
        this.databases = databases;
    }
}
