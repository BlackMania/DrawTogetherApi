package com.api.restapi.models;

import java.util.List;

public class GameHistoryUploadModel {
    public GameHistoryUploadModel() {
    }

    private String gameId;
    private List<String> players;
    private List<RoundModel> rounds;

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public List<String> getPlayers() {
        return players;
    }

    public void setPlayers(List<String> players) {
        this.players = players;
    }

    public List<RoundModel> getRounds() {
        return rounds;
    }

    public void setRounds(List<RoundModel> rounds) {
        this.rounds = rounds;
    }
}
