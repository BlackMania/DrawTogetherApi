package com.api.restapi.resources;

import com.api.dalcomponent.interfaces.IUserRepository;
import com.api.dalcomponent.model.User;
import com.api.restapi.models.LoginModel;
import com.api.restapi.models.TokenModel;
import com.api.logic.tokenlogic.TokenHelper;
import io.jsonwebtoken.JwtException;
import org.json.JSONObject;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("auth")
public class LoginResource {

    private IUserRepository<User> userRepo;


    @Inject
    public LoginResource(IUserRepository<User> userRepo) {
        this.userRepo = userRepo;
    }

    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(LoginModel login) {
        JSONObject response = new JSONObject();
        List<User> allUsers = null;
        try {
            allUsers = userRepo.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(allUsers != null)
        {
            for(User u : allUsers)
            {
                if(u.getUsername().equals(login.getUsername()) && u.getPassword().equals(login.getPassword()))
                {
                    TokenHelper tokenHelper = new TokenHelper();
                    String token = tokenHelper.issueToken(u.getUsername(), u.getClientid());
                    response.put("token", token);
                    return Response
                            .status(Response.Status.OK)
                            .header("Access-Control-Allow-Origin", "*")
                            .entity(response.toString())
                            .build();
                }
            }
        }
        response.put("error", "Login failed");
        return Response
                .status(Response.Status.OK)
                .header("Access-Control-Allow-Origin", "*")
                .entity(response.toString())
                .build();
    }

    @POST
    @Path("tokenauth")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response tokenAuth(TokenModel tokenModel)
    {
        TokenHelper tokenHelper = new TokenHelper();
        try{
            tokenHelper.verifyToken(tokenModel.getToken());
        } catch(JwtException exc)
        {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return Response.status(Response.Status.OK).build();
    }
}
