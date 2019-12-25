package com.api.logic.datalogic.jsonmanager;

import com.api.logic.datalogic.jsonmanager.builders.GameHistoryBuilder;
import com.api.logic.datalogic.processors.GameHistoryProcessor;
import org.json.JSONObject;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

public class JSONResponseBuilderHandler {

    private static Map<ResponseType, ResponseBuilderable> responseBuilders = new HashMap<ResponseType, ResponseBuilderable>();

    static {
        responseBuilders.put(ResponseType.GAMEHISTORY, new GameHistoryBuilder());
    }

    public static JSONObject buildResponse(ResponseType responseType, String clientid)
    {
        return responseBuilders.get(responseType).buildResponse(clientid);
    }
}
