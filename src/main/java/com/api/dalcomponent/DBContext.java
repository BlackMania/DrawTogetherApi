package com.api.dalcomponent;


import com.api.PropertyReader;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

public class DBContext {
    private String connectionString = "";
    private String dbUser = "";
    private String dbPassword = "";

    private ConnectionSource connectionSource;


    public DBContext(){
        PropertyReader reader = new PropertyReader();
        try {
            connectionString = reader.getPropValue("db.url");
            dbUser = reader.getPropValue("db.user");
            dbPassword = reader.getPropValue("db.password");
            this.connectionSource = new JdbcConnectionSource(connectionString, dbUser, dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ConnectionSource getConnectionSource(){
        return connectionSource;
    }
}
