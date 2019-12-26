package com.api.restapi.resources;
import com.api.dalcomponent.interfaces.IGameRepository;
import com.api.dalcomponent.model.Game;
import com.api.logic.datalogic.jsonmanager.JSONResponseBuilderHandler;
import com.api.logic.datalogic.jsonmanager.ResponseType;
import com.api.restapi.response.ResponseBuilder;
import org.json.JSONObject;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Path("secured")
public class GameHistoryResource {

    private IGameRepository<Game> gameRepo;
    private JSONResponseBuilderHandler jsonBuilder;

    @Inject
    public GameHistoryResource(IGameRepository<Game> gameRepo, JSONResponseBuilderHandler jsonBuilder) {
        this.gameRepo = gameRepo;
        this.jsonBuilder = jsonBuilder;
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
    public Response getMyData(@Context HttpHeaders headers){
        JSONObject returnObject = null;
        String token = headers.getRequestHeader("Authorization").get(0);
        token = token.replace("Bearer ", "");
        String[] sections = token.split("\\.");
        byte[] decodedPayload = Base64.getDecoder().decode(sections[1].getBytes());
        try {
            JSONObject obj = new JSONObject(new String(decodedPayload));
            returnObject = jsonBuilder.buildResponse(ResponseType.GAMEHISTORY, obj.getString("clientid"));
        } catch (Exception exc)
        {
            exc.printStackTrace();
        }
        if(returnObject != null)
        {
            return ResponseBuilder.buildResponse(Response.Status.OK, returnObject);
        } else return ResponseBuilder.buildResponse(Response.Status.INTERNAL_SERVER_ERROR);
    }
}
