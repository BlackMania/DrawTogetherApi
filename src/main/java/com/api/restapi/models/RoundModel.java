package com.api.restapi.models;

import java.util.List;

public class RoundModel {
    public RoundModel() {
    }

    private int round;
    private List<DrawDataModel> drawdata;

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public List<DrawDataModel> getDrawdata() {
        return drawdata;
    }

    public void setDrawdata(List<DrawDataModel> drawdata) {
        this.drawdata = drawdata;
    }
}
