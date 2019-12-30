package com.api.dalcomponent;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class DBContext implements IDBContext {
    private String connectionString = "";
    private String dbUser = "";
    private String dbPassword = "";

    private ConnectionSource connectionSource;


    public DBContext(String connectionString, String dbUser, String dbPassword){
        this.connectionString = connectionString;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
        try {
            this.connectionSource = new JdbcConnectionSource(connectionString, dbUser, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ConnectionSource getConnectionSource(){
        return connectionSource;
    }
}
