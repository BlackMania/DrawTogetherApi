package com.api.logic.datalogic.jsonmanager.builders;

import com.api.dalcomponent.model.DrawData;
import com.api.dalcomponent.model.Game;
import com.api.dalcomponent.model.Player;
import com.api.dalcomponent.model.Round;
import com.api.logic.datalogic.interfaces.GameHistoryProcessorable;
import com.api.logic.datalogic.jsonmanager.ResponseBuilderable;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.inject.Inject;
import java.util.List;

public class GameHistoryBuilder implements ResponseBuilderable {

    private GameHistoryProcessorable<Game> processor;

    public GameHistoryBuilder() {
    }

    @Inject
    public GameHistoryBuilder(GameHistoryProcessorable<Game> processorable) {
        this.processor = processorable;
    }

    @Override
    public JSONObject buildResponse(String clientid) {
        JSONObject object = new JSONObject();
        JSONArray allGames = new JSONArray();
        List<Game> games = processor.getByClientId(clientid);
        for(Game game : games)
        {
            JSONObject gameData = new JSONObject();
            gameData.put("gameId", game.getSessionid());
            JSONArray playerArray = new JSONArray();
            for(Player player : game.getPlayers())
            {
                playerArray.put(player.getUser().getUsername());
            }
            gameData.put("players", playerArray);
            JSONArray roundData = new JSONArray();
            for(Round round : game.getRounds())
            {
                JSONObject data = new JSONObject();
                data.put("round", round.getRoundid());
                JSONArray drawData = new JSONArray();
                for(DrawData d : round.getDrawdata())
                {
                    JSONObject obj = new JSONObject();
                    obj.put("prevx", d.getPrevX());
                    obj.put("prevy", d.getPrevY());
                    obj.put("currx", d.getCurrX());
                    obj.put("curry", d.getCurrY());
                    obj.put("linewidth", d.getLinewidth());
                    obj.put("strokestyle", d.getStrokestyle());
                    obj.put("timestamp", d.getTimestamp());
                    drawData.put(obj);
                }
            }
            allGames.put(gameData);
        }
        object.put("gameData", allGames);
        return object;
    }
}
