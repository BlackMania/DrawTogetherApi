package com.api.dalcomponent;

import com.api.dalcomponent.interfaces.IGameRepository;
import com.api.dalcomponent.model.Game;
import com.api.dalcomponent.repository.GameRepository;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GameRepositoryTest {

    IGameRepository<Game> gameRepo;
    public GameRepositoryTest() {
        gameRepo = new GameRepository<Game>(InMemTableUtils.getContext());
    }

    @Test
    public void stage1_getAllGamesTest() {
        List<Game> games = new ArrayList<>();

        games = gameRepo.getAll();

        Assert.assertEquals(1, games.size());

    }

    @Test
    public void stage2_getGameById() {
        Game game = null;

        game = gameRepo.findById(1);

        Assert.assertNotNull(game);
    }

    @Test
    public void stage3_removeGameFromDB()
    {
        Game game = new Game();
        game.setSessionid(UUID.randomUUID().toString());

        gameRepo.save(game);

        Assert.assertEquals(2, gameRepo.getAll().size());

        gameRepo.delete(game);

        Assert.assertEquals(1, gameRepo.getAll().size());
    }

    @Test
    public void stage4_addGameToDB() {
        Game game = new Game();
        game.setSessionid(UUID.randomUUID().toString());

        gameRepo.save(game);

        Assert.assertEquals(2, gameRepo.getAll().size());

        gameRepo.delete(game);

        Assert.assertEquals(1, gameRepo.getAll().size());
    }



    @Test
    public void stage5_getGameBySessionId()
    {
        Game game = new Game();
        game.setSessionid(UUID.randomUUID().toString());

        gameRepo.save(game);

        Assert.assertEquals(1, gameRepo.findByClientId("550e8400-e29b-41d4-a716-446655440000").size());
    }
}
