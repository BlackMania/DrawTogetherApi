package com.api.restapi.resources;
import com.api.dalcomponent.interfaces.IGameRepository;
import com.api.dalcomponent.interfaces.IUserRepository;
import com.api.dalcomponent.model.Game;
import com.api.dalcomponent.model.User;
import com.api.restapi.response.ResponseBuilder;
import org.json.JSONObject;

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
    public GameHistoryResource(IGameRepository<Game> gameRepo) {
        this.gameRepo = gameRepo;
    }


    @GET
    @Path("gamehistory/{clientid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGameDataByUserId(@PathParam("clientid") String clientid){
        List<Game> games = null;
        try {
            games = gameRepo.findByClientId(clientid);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @GET
    @Path("gamehistory/me")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMyData(){
        JSONObject obj = new JSONObject();
        obj.put("Nice", "GOOD");
        return ResponseBuilder.buildResponse(Response.Status.OK, obj);
    }
}
