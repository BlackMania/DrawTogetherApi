package com.api.dalcomponent.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "gamehistory")
public class Game {

    public Game() {
    }

    @DatabaseField(generatedId = true, columnName = "gameid")
    private int gameid;

    @DatabaseField()
    private String sessionid;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<Player> players;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<Round> rounds;

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public ForeignCollection<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ForeignCollection<Player> players) {
        this.players = players;
    }

    public ForeignCollection<Round> getRounds() {
        return rounds;
    }

    public void setRounds(ForeignCollection<Round> rounds) {
        this.rounds = rounds;
    }
}
