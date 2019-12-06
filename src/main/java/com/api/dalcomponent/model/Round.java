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

}

