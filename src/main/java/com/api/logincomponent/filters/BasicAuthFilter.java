package com.api.logincomponent.filters;
import com.api.dalcomponent.DBContext;
import com.api.dalcomponent.interfaces.IUserRepository;
import com.api.dalcomponent.model.User;
import com.api.dalcomponent.repository.UserRepository;
import com.sun.jersey.core.util.Base64;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;


public class BasicAuthFilter implements ContainerRequestFilter {
    private static final String AUTHENTICATION_HEADER_KEY = "Authorization";
    private static final String AUTHENTICATION_HEADER_PREFIX = "Basic ";
    private static final String SECURED_URL_PREFIX = "login";

    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        if (containerRequestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX)) {
            List<String> authHeaders = containerRequestContext.getHeaders().get(AUTHENTICATION_HEADER_KEY);
            if (authHeaders != null) {
                String authToken = authHeaders.get(0);
                if(!authToken.contains(AUTHENTICATION_HEADER_PREFIX))
                {
                    Response badRequestResponse = Response.status(Response.Status.BAD_REQUEST)
                            .entity(Response.Status.BAD_REQUEST.getStatusCode() + " " + Response.Status.BAD_REQUEST.getReasonPhrase())
                            .build();
                    containerRequestContext.abortWith(badRequestResponse);
                    return;
                }
                authToken = authToken.replace(AUTHENTICATION_HEADER_PREFIX, "");
                String decodedString = Base64.base64Decode(authToken);
                StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
                String username = tokenizer.nextToken();
                String password = tokenizer.nextToken();

                try{
                    IUserRepository<User> userRepo = new UserRepository<User>(User.class);
                    for(User u : userRepo.getAll())
                    {
                        if(username.equals(u.getUsername()) && password.equals(u.getPassword()))
                        {
                            return;
                        }
                    }
                }
                catch(Exception exc)
                {
                    Response internalErrorResponse = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode() + " " + Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase())
                            .build();
                    containerRequestContext.abortWith(internalErrorResponse);
                    return;
                }
            }
        }
        else return;

        Response unauthorizedResponse = Response.status(Response.Status.UNAUTHORIZED)
                .entity(Response.Status.UNAUTHORIZED.getStatusCode() + " " + Response.Status.UNAUTHORIZED.getReasonPhrase())
                .build();
        containerRequestContext.abortWith(unauthorizedResponse);
    }

}