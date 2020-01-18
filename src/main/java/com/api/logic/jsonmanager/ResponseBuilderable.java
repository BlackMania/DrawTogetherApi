package com.api.logic.jsonmanager;

import org.json.JSONObject;

public interface ResponseBuilderable {
    JSONObject buildResponse(String clientid);
}
