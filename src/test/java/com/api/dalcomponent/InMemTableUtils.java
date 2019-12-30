package com.api.dalcomponent;

import com.api.dalcomponent.model.User;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.UUID;

public class InMemTableUtils {

    private static IDBContext context;

    public static IDBContext getContext()
    {
        return context;
    }

    public static void generateInMemDatabase() throws SQLException {
        context = new InMemoryDBContext();
        Dao<User, Integer> dao = DaoManager.createDao(context.getConnectionSource(), User.class);

        TableUtils.createTableIfNotExists(context.getConnectionSource(), User.class);

        User user = new User();
        user.setClientid(UUID.randomUUID().toString());
        user.setUsername("testuser");
        // Translates to password
        user.setPassword("10000:9d44c6cb5a3d56de0f3a22609a23a8adbc3e6f5869a10368b1d4b069e260d0bcc0310ab17499bf1af8fef8fba7a8bb934937c81a5bf1c0f11aaa560718ba76a5:cfd0d0a984aa3b1da2bccd2ab3d80b4f1ad30d9e73bd3f62ae0eb2a0ce2b9119e3068226c2a9e55c5c6134ca85e34a9c52e27c4f44f00d791a94762e3c8a0383");
        user.setEmail("testmail@test.nl");

        dao.createIfNotExists(user);
    }
}
