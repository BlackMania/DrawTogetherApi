package com.api.restapi.filter;


import com.api.logic.authentication.AuthHandler;
import com.api.restapi.response.ResponseBuilder;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

public class SecureResourceFilter implements ContainerRequestFilter {
    private static final String AUTHENTICATION_HEADER_KEY = "Authorization";
    private static final String AUTHENTICATION_HEADER_PREFIX = "Bearer ";
    private static final String SECURED_URL_PREFIX = "secured";
    private AuthHandler handler;

    @Inject
    public SecureResourceFilter(AuthHandler handler) {
        this.handler = handler;
    }

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        Response response;
        if(containerRequestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX))
        {
            List<String> authHeaders = containerRequestContext.getHeaders().get(AUTHENTICATION_HEADER_KEY);
            if(authHeaders != null)
            {
                String authToken = authHeaders.get(0);
                authToken = authToken.replace(AUTHENTICATION_HEADER_PREFIX, "");
                try{
                    handler.validateTokenAuthAttempt(authToken);
                    return;
                }
                catch(Exception exc)
                {
                    response = ResponseBuilder.buildResponse(Response.Status.UNAUTHORIZED, Response.Status.UNAUTHORIZED.getReasonPhrase());
                }
            } else {
                response = ResponseBuilder.buildResponse(Response.Status.BAD_REQUEST, Response.Status.BAD_REQUEST.getReasonPhrase());
            }
            containerRequestContext.abortWith(response);
        }
    }
}
