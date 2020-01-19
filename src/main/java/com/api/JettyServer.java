package com.api;

import com.api.restapi.config.APIApp;
import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import java.net.InetSocketAddress;

public class JettyServer {

    private final static Logger logger = Logger.getLogger(JettyServer.class);
    public static void main(String[] args){
        final Server server = new Server(new InetSocketAddress("localhost", 9091));

        ServletContextHandler handler = new ServletContextHandler();
        handler.setContextPath("/api");

        ServletHolder servlet = new ServletHolder(new ServletContainer(new APIApp()));

        handler.addServlet(servlet, "/*");

        server.setHandler(handler);

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
