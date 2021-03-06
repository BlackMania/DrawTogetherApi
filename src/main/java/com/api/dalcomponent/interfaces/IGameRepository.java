package com.api.dalcomponent.interfaces;

import com.api.dalcomponent.model.Game;

import java.util.List;

public interface IGameRepository<T extends Game> extends IRepository<Game>  {
    List<Game> findByClientId(String id);
}
