package com.api.restapi.config;

import com.api.restapi.filter.SecureResourceFilter;
import com.api.restapi.resources.GameHistoryResource;
import com.api.restapi.resources.LoginResource;
import com.api.restapi.resources.RegisterResource;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("api")
public class APIApp extends ResourceConfig {
    public APIApp() {
        register(new InjectBinder());
        register(GameHistoryResource.class);
        register(CorsProvider.class);
        register(LoginResource.class);
        register(RegisterResource.class);
        register(SecureResourceFilter.class);
    }

}
