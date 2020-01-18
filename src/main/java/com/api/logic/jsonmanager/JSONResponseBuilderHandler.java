package com.api.logic.jsonmanager;

import com.api.logic.jsonmanager.builders.GameHistoryBuilder;
import org.json.JSONObject;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

public class JSONResponseBuilderHandler {

    private Map<ResponseType, ResponseBuilderable> responseBuilders = new HashMap<ResponseType, ResponseBuilderable>();

    @Inject
    public JSONResponseBuilderHandler(GameHistoryBuilder gameBuilder) {
        responseBuilders.put(ResponseType.GAMEHISTORY, gameBuilder);
    }

    public JSONObject buildResponse(ResponseType responseType, String clientid)
    {
        return responseBuilders.get(responseType).buildResponse(clientid);
    }
}
