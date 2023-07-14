package com.zkl.tournamentinfoservice.sportsdata.model;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class LeaderboardResponse {
    private Tournament tournament;
    @JsonProperty("Players")
    private List<PlayerScore> playerScores;

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public List<PlayerScore> getPlayerScores() {
        return playerScores;
    }

    public void setPlayerScores(List<PlayerScore> playerScores) {
        this.playerScores = playerScores;
    }
}
