package com.api.logic.datalogic.processors;

import com.api.dalcomponent.interfaces.IGameRepository;
import com.api.dalcomponent.model.Game;
import com.api.logic.datalogic.interfaces.GameHistoryProcessorable;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameHistoryProcessor<T extends Game> extends DataProcessor<Game> implements GameHistoryProcessorable<Game> {
    @Inject
    public GameHistoryProcessor(IGameRepository<Game> repo) {
        super(repo);
    }

    public List<Game> getByClientId(String clientid)
    {
        List<Game> games = new ArrayList<>();
        IGameRepository<Game> gameRepo = (IGameRepository<Game>)repo;
        try {
            games = gameRepo.findByClientId(clientid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return games;
    }
}
