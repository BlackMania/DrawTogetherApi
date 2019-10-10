package com.api;

import com.api.logincomponent.filters.BasicAuthFilter;
import com.api.logincomponent.filters.TokenAuthFilter;
import com.api.logincomponent.resources.LoginResource;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("api")
public class APIApp extends ResourceConfig {
    public APIApp() {
        register(LoginResource.class);
        register(BasicAuthFilter.class);
        register(TokenAuthFilter.class);
    }
}
