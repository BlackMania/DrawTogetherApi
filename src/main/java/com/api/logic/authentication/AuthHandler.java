package com.api.logic.authentication;

import com.api.dalcomponent.interfaces.IUserRepository;
import com.api.dalcomponent.model.User;
import com.auth0.jwt.exceptions.JWTVerificationException;
import io.jsonwebtoken.JwtException;

import javax.inject.Inject;
import javax.naming.AuthenticationException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public class AuthHandler {
    private HashValidator validator;
    private IUserRepository<User> userRepo;
    private TokenHelper tokenHelper;
    private String token;

    @Inject
    public AuthHandler(IUserRepository<User> userRepo) {
        this.userRepo = userRepo;
        this.validator = new HashValidator();
        this.tokenHelper = new TokenHelper();
    }

    public boolean validateLoginAttempt(String username, String password)
    {
        try {
            for(User user : userRepo.getAll())
            {
                if(user.getUsername().equals(username))
                {
                    if(validator.validatePassword(password, user.getPassword()))
                    {
                        token = tokenHelper.issueToken(user.getUsername(), user.getClientid());
                        return true;
                    }
                }
            }
        } catch (SQLException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean validateTokenAuthAttempt(String token)
    {
        try {
            tokenHelper.verifyToken(token);
        } catch (JwtException exc) {
            return false;
        }
        return true;
    }

    public String getToken()
    {
        return token;
    }
}
