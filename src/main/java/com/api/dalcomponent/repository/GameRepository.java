package com.api.dalcomponent.repository;

import com.api.dalcomponent.IDBContext;
import com.api.dalcomponent.interfaces.IGameRepository;
import com.api.dalcomponent.model.Game;
import com.api.dalcomponent.model.Player;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameRepository<T extends Game> extends Repository<Game> implements IGameRepository<Game> {


    @Inject
    public GameRepository(IDBContext dbContext) {
        super(Game.class, dbContext);
    }

    @Override
    public List<Game> findByClientId(String id) {
        List<Game> data = new ArrayList<>();

        try {
            data = dao.queryForAll();
        } catch (SQLException exc) {
            logger.error(exc);
        }
        List<Game> returnList = new ArrayList<>();
        for (Game game : data) {
            for (Player player : game.getPlayers()) {
                if (player.getUser().getClientid().equals(id)) {
                    returnList.add(game);
                    break;
                }
            }
        }

        return returnList;
    }
}