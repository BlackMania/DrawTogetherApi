package com.api.dalcomponent.model;

import com.j256.ormlite.field.DatabaseField;

import java.sql.Timestamp;

public class DrawData {
    @DatabaseField(generatedId = true, columnName = "drawdataid")
    private int drawdataid;
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

    public int getDrawdataid() {
        return drawdataid;
    }

    public void setDrawdataid(int drawdataid) {
        this.drawdataid = drawdataid;
    }

    public int getCurrX() {
        return currX;
    }

    public void setCurrX(int currX) {
        this.currX = currX;
    }

    public int getCurrY() {
        return currY;
    }

    public void setCurrY(int currY) {
        this.currY = currY;
    }

    public int getPrevX() {
        return prevX;
    }

    public void setPrevX(int prevX) {
        this.prevX = prevX;
    }

    public int getPrevY() {
        return prevY;
    }

    public void setPrevY(int prevY) {
        this.prevY = prevY;
    }

    public String getStrokestyle() {
        return strokestyle;
    }

    public void setStrokestyle(String strokestyle) {
        this.strokestyle = strokestyle;
    }

    public int getLinewidth() {
        return linewidth;
    }

    public void setLinewidth(int linewidth) {
        this.linewidth = linewidth;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }
}