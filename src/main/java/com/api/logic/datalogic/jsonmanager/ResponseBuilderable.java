package com.api.logic.datalogic.jsonmanager;

import org.json.JSONObject;

public interface ResponseBuilderable {
    JSONObject buildResponse(String clientid);
}
