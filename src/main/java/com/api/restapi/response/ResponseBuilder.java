package com.api.restapi.response;

import org.json.JSONObject;

import javax.ws.rs.core.Response;

public class ResponseBuilder {
    public static Response buildResponse(Response.Status statusCode, JSONObject responseBody)
    {
        return Response
            .status(statusCode)
            .header("Access-Control-Allow-Origin", "*")
            .entity(responseBody.toString())
            .build();
    }

    public static Response buildResponse(Response.Status statusCode, String responseBody)
    {
        return Response
                .status(statusCode)
                .header("Access-Control-Allow-Origin", "*")
                .entity(responseBody)
                .build();
    }

    public static Response buildResponse(Response.Status statusCode)
    {
        return Response
                .status(statusCode)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }
}
