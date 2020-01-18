package com.api.restapi.models;

import java.sql.Timestamp;
import java.util.Date;

public class DrawDataModel {
    public DrawDataModel() {
    }

    private String strokestyle;
    private int prevy;
    private int prevx;
    private int currx;
    private int curry;
    private int linewidth;
    private String timestamp;

    public String getStrokestyle() {
        return strokestyle;
    }

    public void setStrokestyle(String strokestyle) {
        this.strokestyle = strokestyle;
    }

    public int getPrevy() {
        return prevy;
    }

    public void setPrevy(int prevy) {
        this.prevy = prevy;
    }

    public int getPrevx() {
        return prevx;
    }

    public void setPrevx(int prevx) {
        this.prevx = prevx;
    }

    public int getCurrx() {
        return currx;
    }

    public void setCurrx(int currx) {
        this.currx = currx;
    }

    public int getCurry() {
        return curry;
    }

    public void setCurry(int curry) {
        this.curry = curry;
    }

    public int getLinewidth() {
        return linewidth;
    }

    public void setLinewidth(int linewidth) {
        this.linewidth = linewidth;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
