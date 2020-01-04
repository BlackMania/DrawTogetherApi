package com.api.dalcomponent;

import com.api.dalcomponent.model.*;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

public class InMemTableUtils {

    private static IDBContext context;

    public static IDBContext getContext()
    {
        return context;
    }

    static {
        try {
            generateInMemDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void generateInMemDatabase() throws SQLException {
        context = new InMemoryDBContext();
        Dao<User, Integer> userDao = DaoManager.createDao(context.getConnectionSource(), User.class);
        Dao<Game, Integer> gameDao = DaoManager.createDao(context.getConnectionSource(), Game.class);
        Dao<Player, Integer> playerDao = DaoManager.createDao(context.getConnectionSource(), Player.class);
        Dao<Round, Integer> roundDao = DaoManager.createDao(context.getConnectionSource(), Round.class);
        Dao<DrawData, Integer> drawDataDao = DaoManager.createDao(context.getConnectionSource(), DrawData.class);

        TableUtils.createTableIfNotExists(context.getConnectionSource(), User.class);
        TableUtils.createTableIfNotExists(context.getConnectionSource(), Game.class);
        TableUtils.createTableIfNotExists(context.getConnectionSource(), Player.class);
        TableUtils.createTableIfNotExists(context.getConnectionSource(), Round.class);
        TableUtils.createTableIfNotExists(context.getConnectionSource(), DrawData.class);


        User user = new User();
        user.setClientid("550e8400-e29b-41d4-a716-446655440000");
        user.setUsername("testuser");
        // Translates to password
        user.setPassword("10000:9d44c6cb5a3d56de0f3a22609a23a8adbc3e6f5869a10368b1d4b069e260d0bcc0310ab17499bf1af8fef8fba7a8bb934937c81a5bf1c0f11aaa560718ba76a5:cfd0d0a984aa3b1da2bccd2ab3d80b4f1ad30d9e73bd3f62ae0eb2a0ce2b9119e3068226c2a9e55c5c6134ca85e34a9c52e27c4f44f00d791a94762e3c8a0383");
        user.setEmail("testmail@test.nl");


        userDao.createIfNotExists(user);
        DrawData drawData = new DrawData();
        drawData.setCurrX(2);
        drawData.setCurrY(2);
        drawData.setPrevX(2);
        drawData.setPrevY(2);
        drawData.setLinewidth(2);
        drawData.setStrokestyle("black");
        drawData.setTimestamp(new Timestamp(new Date().getTime()));

        Game game = new Game();
        game.setSessionid(UUID.randomUUID().toString());

        Round round = new Round();
        round.setGame(game);

        drawData.setRound(round);

        Player player = new Player();
        player.setUser(user);
        player.setGame(game);

        gameDao.createIfNotExists(game);
        playerDao.create(player);
        roundDao.create(round);
        drawDataDao.create(drawData);
    }
}
