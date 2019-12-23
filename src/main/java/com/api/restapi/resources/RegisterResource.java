package com.api.restapi.resources;

import com.api.logic.authentication.RegisterHandler;
import com.api.restapi.inputvalidator.InputValidator;
import com.api.restapi.models.RegisterModel;
import com.api.restapi.response.ResponseBuilder;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class RegisterResource {
    private RegisterHandler handler;

    @Inject
    public RegisterResource(RegisterHandler handler) {
        this.handler = handler;
    }

    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(RegisterModel registerInput)
    {
        if(InputValidator.validateEmailInput(registerInput.getEmail()) && InputValidator.validatePasswordInput(registerInput.getPassword()))
        {
            try {
                handler.registerUser(registerInput.getUsername(), registerInput.getPassword(), registerInput.getEmail());
            }
            catch (Exception exc)
            {
                exc.printStackTrace();
                return ResponseBuilder.buildResponse(Response.Status.INTERNAL_SERVER_ERROR, "Something went wrong registrating you. Please try again");
            }
            return ResponseBuilder.buildResponse(Response.Status.OK, "You have been succesfully registered");
        }
        return ResponseBuilder.buildResponse(Response.Status.BAD_REQUEST, "Userinput was invalid");
    }
}
