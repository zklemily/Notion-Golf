package com.zkl.tournamentinfoservice.controller;

import com.zkl.tournamentinfoservice.sportsdata.model.Player;
import com.zkl.tournamentinfoservice.sportsdata.service.SportsdataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final SportsdataService sportsdataService;

    @Autowired
    public PlayerController(SportsdataService sportsdataService) {
        this.sportsdataService = sportsdataService;
    }

    @GetMapping
    public List<Player> getAllPlayers() throws IOException, InterruptedException {
        List<Player> players = sportsdataService.getPlayers();
        return players;
    }

    @GetMapping("/{playerId}")
    public Player getPlayer() throws IOException, InterruptedException {
        Player player = sportsdataService.getPlayer("40000509");
        return player;
    }
}
