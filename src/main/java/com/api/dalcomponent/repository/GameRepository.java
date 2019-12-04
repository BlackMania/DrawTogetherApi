package com.api.dalcomponent.repository;

import com.api.dalcomponent.interfaces.IGameRepository;

public class GameRepository<Game> extends Repository<Game> implements IGameRepository<Game> {
    public GameRepository(Class<Game> clazz) {
        super(clazz);
    }
}
