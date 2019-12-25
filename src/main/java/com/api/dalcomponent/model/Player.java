package com.api.dalcomponent.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "gameplayers")
public class Player {

    @DatabaseField(columnName = "gameid", index = true)
    private int gameid;

    @DatabaseField(columnName = "accountid", index = true, id = true)
    private int accountid;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "gameid", index = true)
    private Game game;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "accountid", index = true)
    private User user;

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
