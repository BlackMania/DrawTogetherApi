package com.api.logincomponent.resources;

import com.api.logincomponent.tokenlogic.TokenHelper;
import com.sun.jersey.core.util.Base64;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.util.StringTokenizer;

@Path("secured")
public class LoginResource {

    @POST
    @Path("login")
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(@Context HttpHeaders headers){
        TokenHelper tokenHelper = new TokenHelper();
        String userInfo = headers.getRequestHeader("Authorization").get(0);
        userInfo = userInfo.replace("Basic ", "");
        String decodedString = Base64.base64Decode(userInfo);
        StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
        String username = tokenizer.nextToken();
        JsonObject object = Json.createObjectBuilder()
                .add("token", tokenHelper.issueToken(username))
                .build();
        return Response
                .status(Response.Status.OK)
                .header("Access-Control-Allow-Origin", "*")
                .entity(object.toString())
                .build();
    }

    @POST
    @Path("tokenauth")
    public Response verify() {
        return Response
                .status(Response.Status.OK)
                .header("Access-Control-Allow-Origin", "*")
                .entity(Response.Status.OK.getStatusCode() + " " + Response.Status.OK.toString())
                .build();
    }
}

