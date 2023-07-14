package com.zkl.tournamentinfoservice.controller;

import com.zkl.tournamentinfoservice.sportsdata.model.News;
import com.zkl.tournamentinfoservice.sportsdata.service.SportsdataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {
    private final SportsdataService sportsdataService;

    @Autowired
    public NewsController(SportsdataService sportsdataService) {
        this.sportsdataService = sportsdataService;
    }

    @GetMapping
    public List<News> getTodayNews() throws IOException, InterruptedException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = LocalDate.now().format(formatter);
        List<News> news = sportsdataService.getNews(date);
        return news;
    }
}
