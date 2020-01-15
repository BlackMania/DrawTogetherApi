package com.api.dalcomponent;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;

import java.sql.SQLException;

public class DBContext implements IDBContext {

    private ConnectionSource connectionSource;


    private final static Logger logger = Logger.getLogger(DBContext.class);


    public DBContext(String connectionString, String dbUser, String dbPassword){
        try {
            this.connectionSource = new JdbcConnectionSource(connectionString, dbUser, dbPassword);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public ConnectionSource getConnectionSource(){
        return connectionSource;
    }
}
