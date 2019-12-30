package com.api.dalcomponent;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class InMemoryDBContext implements IDBContext{
    private ConnectionSource connectionSource;

    public InMemoryDBContext() {
        try {
            connectionSource = new JdbcPooledConnectionSource("jdbc:h2:mem:myDb");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ConnectionSource getConnectionSource() {
        return connectionSource;
    }
}
