package com.api.restapi.resources;

import com.api.logic.authentication.LoginHandler;
import com.api.restapi.models.LoginModel;
import com.api.restapi.models.TokenModel;
import com.api.logic.authentication.TokenHelper;
import com.api.restapi.response.ResponseBuilder;
import io.jsonwebtoken.JwtException;
import org.json.JSONObject;

import javax.inject.Inject;
import javax.naming.AuthenticationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("auth")
public class LoginResource {

    private LoginHandler handler;

    @Inject
    public LoginResource(LoginHandler handler) {
        this.handler = handler;
    }

    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(LoginModel login) {
        JSONObject json = new JSONObject();
        TokenHelper tokenHelper = new TokenHelper();
        String token;

        try {
            token = handler.login(login.getUsername(), login.getPassword());
            json.put("token", token);
        } catch (AuthenticationException e) {
            json.put("error", e.getMessage());
            return Response
                    .status(Response.Status.OK)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity(json.toString())
                    .build();
        }
        return Response
                .status(Response.Status.OK)
                .header("Access-Control-Allow-Origin", "*")
                .entity(json.toString())
                .build();
    }

    @POST
    @Path("tokenauth")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response tokenAuth(TokenModel tokenModel) {
        TokenHelper tokenHelper = new TokenHelper();
        try {
            tokenHelper.verifyToken(tokenModel.getToken());
        } catch (JwtException exc) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return Response.status(Response.Status.OK).build();
    }
}
