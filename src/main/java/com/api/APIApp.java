package com.api;

import com.api.restapi.resources.GameHistoryResource;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("api")
public class APIApp extends ResourceConfig {
    public APIApp() {
        register(new InjectBinder());
        register(GameHistoryResource.class);
        register(CorsProvider.class);
    }

}
