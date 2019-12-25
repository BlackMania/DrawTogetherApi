package com.api.dalcomponent.repository;

import com.api.dalcomponent.interfaces.IGameRepository;
import com.api.dalcomponent.model.Game;
import com.j256.ormlite.dao.GenericRawResults;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameRepository<T extends Game> extends Repository<Game> implements IGameRepository<Game> {
    @Inject
    public GameRepository() {
        super(Game.class);
    }

    @Override
    public List<Game> findByClientId(String id) throws SQLException {
        List<Game> returnList = new ArrayList<>();
        GenericRawResults<Game> result =  dao.queryRaw("SELECT * FROM gamehistory INNER JOIN gameplayers ON gameplayers.gameid = gamehistory.gameid INNER JOIN round ON round.gameid = gamehistory.gameid INNER JOIN drawdata ON drawdata.roundid = round.roundid INNER JOIN account ON account.accountid = gameplayers.accountid WHERE account.clientid = " + id, dao.getRawRowMapper());
        for(Game g : result)
        {
            returnList.add(g);
        }
        return returnList;
    }
}