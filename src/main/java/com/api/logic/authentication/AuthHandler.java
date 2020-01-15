package com.api.logic.authentication;

import com.api.dalcomponent.interfaces.IUserRepository;
import com.api.dalcomponent.model.User;
import io.jsonwebtoken.JwtException;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class AuthHandler {
    private HashValidator validator;
    private IUserRepository<User> userRepo;
    private TokenHelper tokenHelper;
    private String token;

    private final static Logger logger = Logger.getLogger(AuthHandler.class);

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
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            logger.error(e);
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
