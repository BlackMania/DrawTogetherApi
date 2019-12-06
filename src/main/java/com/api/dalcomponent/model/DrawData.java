package com.api.dalcomponent.model;

import com.j256.ormlite.field.DatabaseField;

import java.sql.Timestamp;

public class DrawData {
    @DatabaseField(id = true, columnName = "drawdataid")
    private int drawdataid;

    @DatabaseField(columnName = "roundid")
    private int roundid;

    @DatabaseField(columnName = "currx")
    private int currX;
    @DatabaseField(columnName = "currY")
    private int currY;
    @DatabaseField(columnName = "prevX")
    private int prevX;
    @DatabaseField(columnName = "prevY")
    private int prevY;
    @DatabaseField(columnName = "strokestyle")
    private String strokestyle;
    @DatabaseField(columnName = "linewidth")
    private int linewidth;
    @DatabaseField(columnName = "timestamp")
    private Timestamp timestamp;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "roundid", index = true)
    private Round round;
}