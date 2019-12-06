package com.api.restapi.resources;

import com.api.dalcomponent.interfaces.IGameRepository;
import com.api.dalcomponent.model.Game;
import com.api.dalcomponent.repository.GameRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("secured")
public class GameHistoryResource {

    private IGameRepository<Game> gameRepo;

    @Inject
    public GameHistoryResource(IGameRepository<Game> game) {
        this.gameRepo = game;
    }


    @GET
    @Path("gamehistory/{clientid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGameData(@PathParam("clientid") String clientid){
        List<Game> games = null;
        try {
            games = gameRepo.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.print(games.get(0));

        return null;
    }
}
