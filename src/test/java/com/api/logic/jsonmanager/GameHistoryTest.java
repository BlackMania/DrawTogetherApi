package com.api.logic.jsonmanager;

import com.api.dalcomponent.InMemTableUtils;
import com.api.dalcomponent.repository.GameRepository;
import com.api.logic.jsonmanager.builders.GameHistoryBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class GameHistoryTest {

    GameHistoryBuilder builder;

    public GameHistoryTest() {
        builder = new GameHistoryBuilder(new GameRepository<>(InMemTableUtils.getContext()));
    }

    @Test
    public void buildResponseWithCorrectIDGiven() {
        JSONObject object = builder.buildResponse("550e8400-e29b-41d4-a716-446655440000");

        Assert.assertNotNull(object.getJSONArray("gameData"));
        Object obj = object.getJSONArray("gameData").get(0);
        JSONObject game = (JSONObject) object.getJSONArray("gameData").get(0);

        Assert.assertNotNull(game);
        Assert.assertNotNull(game.getString("gameId"));
    }


    // This test shows that there is an object returned with a key named gameData.
    // But there is no data since there is no user with this particularly clientid.
    @Test
    public void buildResponseWithIDWhichIsNotInDatabase() {
        JSONObject object = builder.buildResponse("550e8400-e29b-41d4-a716-445440000");

        Assert.assertNotNull(object.getJSONArray("gameData"));
        Assert.assertThrows(JSONException.class, () -> {
            Object obj = object.getJSONArray("gameData").get(0);
        });
    }

    // This test shows that there is an object returned with a key named gameData.
    // But there is no data since there is no user with this particularly clientid since this is null.
    @Test
    public void buildResponseWithInvalidParameter() {
        JSONObject object = builder.buildResponse(null);

        Assert.assertNotNull(object.getJSONArray("gameData"));
        Assert.assertThrows(JSONException.class, () -> {
            Object obj = object.getJSONArray("gameData").get(0);
        });
    }
}
