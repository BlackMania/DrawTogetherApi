package com.api.dalcomponent.repository;

import com.api.dalcomponent.interfaces.IGameRepository;
import com.api.dalcomponent.model.Game;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

public class GameRepository<T extends Game> extends Repository<Game> implements IGameRepository<Game> {
    @Inject
    public GameRepository() {
        super(Game.class);
    }

    @Override
    public List<Game> findByClientId(String id) throws SQLException {
        return null;
    }
}