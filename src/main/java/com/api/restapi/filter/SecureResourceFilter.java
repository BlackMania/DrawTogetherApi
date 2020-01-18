package com.api.restapi.filter;


import com.api.PropertyReader;
import com.api.logic.authentication.AuthHandler;
import com.api.restapi.response.ResponseBuilder;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import java.util.List;

public class SecureResourceFilter implements ContainerRequestFilter {
    private static final String AUTHENTICATION_HEADER_KEY = "Authorization";
    private static final String AUTHENTICATION_HEADER_PREFIX = "Bearer ";
    private static final String AUTHENTICATION_SHAREDKEY_PREFIX = "SharedKey";
    private static final String SECURED_URL_PREFIX = "secured";
    private AuthHandler handler;
    private PropertyReader reader;

    @Inject
    public SecureResourceFilter(AuthHandler handler) {
        this.handler = handler;
        this.reader = new PropertyReader();
    }

    @Override
    public void filter(ContainerRequestContext containerRequestContext) {
        Response response;
        if (containerRequestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX)) {
            List<String> authHeaders = containerRequestContext.getHeaders().get(AUTHENTICATION_HEADER_KEY);
            List<String> sharedKeyHeaders = containerRequestContext.getHeaders().get(AUTHENTICATION_SHAREDKEY_PREFIX);
            if(sharedKeyHeaders != null)
            {
                if(sharedKeyHeaders.get(0).equals(reader.getPropValue("sharedkey"))) return;
            }
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
