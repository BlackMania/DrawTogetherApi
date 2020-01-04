package com.api.logic.jsonmanager;

import com.api.dalcomponent.InMemTableUtils;
import com.api.dalcomponent.repository.GameRepository;
import com.api.logic.jsonmanager.builders.GameHistoryBuilder;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class JSONResponseBuilderHandlerTest {

    private JSONResponseBuilderHandler handler;

    public JSONResponseBuilderHandlerTest() {
        GameHistoryBuilder builder = new GameHistoryBuilder(new GameRepository<>(InMemTableUtils.getContext()));
        handler = new JSONResponseBuilderHandler(builder);
    }

    @Test
    public void receiveAJSONObjectWhichContainsGameDataTest() {
        JSONObject object = handler.buildResponse(ResponseType.GAMEHISTORY, "550e8400-e29b-41d4-a716-446655440000");

        Assert.assertNotNull(object.getJSONArray("gameData"));
        Object obj = object.getJSONArray("gameData").get(0);
        JSONObject game = (JSONObject) object.getJSONArray("gameData").get(0);

        Assert.assertNotNull(game);
        Assert.assertNotNull(game.getString("gameId"));
    }

    @Test
    public void runHandlerWithNullAsEnum()
    {
        Assert.assertThrows(NullPointerException.class, () -> {
            JSONObject object = handler.buildResponse(null, "550e8400-e29b-41d4-a716-446655440000");
        });
    }
}
