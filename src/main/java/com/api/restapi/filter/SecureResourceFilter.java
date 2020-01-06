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
    public void filter(ContainerRequestContext containerRequestContext) {
        Response response;
        if (containerRequestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX)) {
            List<String> authHeaders = containerRequestContext.getHeaders().get(AUTHENTICATION_HEADER_KEY);
            if (authHeaders != null) {
                String authToken = authHeaders.get(0);
                authToken = authToken.replace(AUTHENTICATION_HEADER_PREFIX, "");
                if (handler.validateTokenAuthAttempt(authToken)) {
                    return;
                } else {
                    response = ResponseBuilder.buildResponse(Response.Status.UNAUTHORIZED, Response.Status.UNAUTHORIZED.getReasonPhrase());
                    containerRequestContext.abortWith(response);
                }
            }
        }
    }
}
