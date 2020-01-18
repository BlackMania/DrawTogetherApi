package com.api.restapi.resources;

import com.api.logic.authentication.AuthHandler;
import com.api.restapi.models.LoginModel;
import com.api.restapi.response.ResponseBuilder;
import org.json.JSONObject;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("auth")
public class LoginResource {

    private AuthHandler handler;

    @Inject
    public LoginResource(AuthHandler handler) {
        this.handler = handler;
    }

    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(LoginModel login) {
        JSONObject json = new JSONObject();
        String token;

        if(handler.validateLoginAttempt(login.getUsername(), login.getPassword()))
        {
            token = handler.getToken();
            json.put("token", token);
        }
        else {
            json.put("error", "Invalid login credentials");
        }
        return ResponseBuilder.buildResponse(Response.Status.OK, json);
    }

    @POST
    @Path("tokenauth")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response tokenAuth(@Context HttpHeaders header) {
        String token = header.getRequestHeader("Authorization").get(0);
        token = token.replace("Bearer ", "");
        if(handler.validateTokenAuthAttempt(token))
        {
            return ResponseBuilder.buildResponse(Response.Status.OK);
        } else return ResponseBuilder.buildResponse(Response.Status.UNAUTHORIZED);
    }
}
