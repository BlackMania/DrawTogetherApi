package com.api.dalcomponent;

import com.api.dalcomponent.interfaces.IGameRepository;
import com.api.dalcomponent.interfaces.IRoundRepository;
import com.api.dalcomponent.model.Game;
import com.api.dalcomponent.model.Round;
import com.api.dalcomponent.repository.GameRepository;
import com.api.dalcomponent.repository.RoundRepository;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class DatabaseCreationTest {

    IGameRepository<Game> gameRepo;
    IRoundRepository<Round> roundRepo;

    public DatabaseCreationTest() {
        gameRepo = new GameRepository<Game>(InMemTableUtils.getContext());
        roundRepo = new RoundRepository<Round>(InMemTableUtils.getContext());
    }

    @Test
    public void TestDatabaseDataCreation() throws SQLException {
        List<Game> games = gameRepo.getAll();
        Round round = roundRepo.findById(1);

        Assert.assertEquals(1, games.size());
        Assert.assertEquals(1, games.get(0).getPlayers().size());
        Assert.assertEquals(1, games.get(0).getRounds().size());
        Assert.assertEquals(1, round.getDrawdata().size());
    }
}
