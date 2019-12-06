package com.api.dalcomponent.repository;


import com.api.dalcomponent.interfaces.IGameRepository;
import com.api.dalcomponent.model.Game;

import javax.inject.Inject;


public class GameRepository<T extends Game> extends Repository<Game> implements IGameRepository<Game> {

    @Inject
    public GameRepository() {
        super(Game.class);
    }
}
