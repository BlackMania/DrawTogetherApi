package com.api.dalcomponent.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "gameplayers")
public class Player {
    @DatabaseField(id = true)
    private int gameid;

    @DatabaseField(columnName = "accountid")
    private int accountid;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "gameid")
    private Player player;
}
