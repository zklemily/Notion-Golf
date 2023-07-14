package com.zkl.tournamentinfoservice.controller;

import com.zkl.tournamentinfoservice.sportsdata.model.PlayerScore;
import com.zkl.tournamentinfoservice.sportsdata.model.Tournament;
import com.zkl.tournamentinfoservice.sportsdata.service.SportsdataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/tournaments")
public class SportsdataController {
    private final SportsdataService sportsdataService;

    @Autowired
    public SportsdataController(SportsdataService sportsdataService) {
        this.sportsdataService = sportsdataService;
    }

    @GetMapping
    public List<Tournament> getTournaments() throws IOException, InterruptedException {
        List<Tournament> tournaments = sportsdataService.getTournaments();
        for (int i = 0; i < 10; i++) {
            System.out.println(tournaments.get(i));
        }
        return tournaments;
    }

    @GetMapping("/{tournamentId}")
    public List<PlayerScore> getLeaderboard() throws IOException, InterruptedException {
        List<PlayerScore> playerScores = sportsdataService.getLeaderboard("555");
        return playerScores;
    }
}
