package com.api.restapi.resources;
import com.api.dalcomponent.model.Game;;
import com.api.logic.jsonmanager.JSONResponseBuilderHandler;
import com.api.logic.jsonmanager.ResponseType;
import com.api.restapi.models.GameHistoryUploadModel;
import com.api.restapi.response.ResponseBuilder;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Base64;
import java.util.List;

@Path("secured")
public class GameHistoryResource {

    private JSONResponseBuilderHandler jsonBuilder;

    private final static Logger logger = Logger.getLogger(GameHistoryResource.class);

    @Inject
    public GameHistoryResource(JSONResponseBuilderHandler jsonBuilder) {
        this.jsonBuilder = jsonBuilder;
    }


/*    @GET
    @Path("gamehistory/{clientid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGameDataByUserId(@PathParam("clientid") String clientid){
        return null;
    }*/

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
            logger.error(exc);
        }
        if(returnObject != null)
        {
            return ResponseBuilder.buildResponse(Response.Status.OK, returnObject);
        } else return ResponseBuilder.buildResponse(Response.Status.INTERNAL_SERVER_ERROR);

    }

    @POST
    @Path("gamehistory/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void uploadGameHistory(GameHistoryUploadModel model)
    {
        System.out.println("got here");
    }
}
