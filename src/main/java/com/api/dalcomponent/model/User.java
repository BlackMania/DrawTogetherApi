package com.api.dalcomponent.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "account")
public class User {
    @DatabaseField(columnName = "accountid", id = true)
    private int accountid;

    @DatabaseField(columnName = "clientid")
    private String clientid;

    @DatabaseField(columnName = "username")
    private String username;

    @DatabaseField(columnName = "password")
    private String password;

    @ForeignCollectionField(eager=false, columnName = "accountid")
    private ForeignCollection<Player> player;

    //region getters and setters
    public int getId() {
        return accountid;
    }

    public void setId(int id) {
        this.accountid = id;
    }

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ForeignCollection<Player> getPlayer() {
        return player;
    }

    public void setPlayer(ForeignCollection<Player> player) {
        this.player = player;
    }

    //endregion
}