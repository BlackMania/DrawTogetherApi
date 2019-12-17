package com.api.restapi.filter;


import com.api.logic.authentication.TokenHelper;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

public class SecureResourceFilter implements ContainerRequestFilter {
    private static final String AUTHENTICATION_HEADER_KEY = "Authorization";
    private static final String AUTHENTICATION_HEADER_PREFIX = "Bearer ";
    private static final String SECURED_URL_PREFIX = "secured";

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        if(containerRequestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX))
        {
            List<String> authHeaders = containerRequestContext.getHeaders().get(AUTHENTICATION_HEADER_KEY);
            if(authHeaders != null)
            {
                String authToken = authHeaders.get(0);
                if(!authToken.contains(AUTHENTICATION_HEADER_PREFIX))
                {
                    Response badRequestResponse = Response.status(Response.Status.BAD_REQUEST).entity(Response.Status.BAD_REQUEST.getStatusCode() + " " + Response.Status.BAD_REQUEST.getReasonPhrase()).build();
                    containerRequestContext.abortWith(badRequestResponse);
                    return;
                }
                authToken = authToken.replace(AUTHENTICATION_HEADER_PREFIX, "");
                TokenHelper tokenHelper = new TokenHelper();
                try{
                    tokenHelper.verifyToken(authToken);
                }
                catch(Exception exc)
                {
                    Response unauthorizedResponse = Response.status(Response.Status.UNAUTHORIZED).entity(Response.Status.UNAUTHORIZED.getStatusCode() + " " + Response.Status.UNAUTHORIZED.getReasonPhrase()).build();
                    containerRequestContext.abortWith(unauthorizedResponse);
                }
            }
        }
    }
}
