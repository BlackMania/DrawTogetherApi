package com.api.restapi.resources;

import com.api.restapi.models.LoginModel;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("auth")
public class LoginResource {
    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(LoginModel login) {
        System.out.printf(login.getUsername());
        return null;
    }
}
