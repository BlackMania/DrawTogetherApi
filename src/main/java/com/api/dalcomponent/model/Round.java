package com.api.dalcomponent.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "round")
public class Round {
    @DatabaseField(columnName = "gameid")
    private int gameid;

    @DatabaseField(id = true, columnName = "roundid")
    private int roundid;

    @DatabaseField(foreign = true, columnName = "gameid")
    private Game game;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<DrawData> drawdata;

    public int getGameid() {
        return gameid;
    }

    public void setGameid(int gameid) {
        this.gameid = gameid;
    }

    public int getRoundid() {
        return roundid;
    }

    public void setRoundid(int roundid) {
        this.roundid = roundid;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public ForeignCollection<DrawData> getDrawdata() {
        return drawdata;
    }

    public void setDrawdata(ForeignCollection<DrawData> drawdata) {
        this.drawdata = drawdata;
    }
}

