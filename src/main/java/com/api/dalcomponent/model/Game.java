package com.api.dalcomponent.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "gamehistory")
public class Game {
    @DatabaseField(id=true)
    private int gameid;

    @DatabaseField(columnName = "sessionid")
    private String sessionid;

    @ForeignCollectionField
    private ForeignCollection<Player> players;

    //region getters and setters
    public int getGameid() {
        return gameid;
    }

    public void setGameid(int gameid) {
        this.gameid = gameid;
    }

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
    //endregion
}
