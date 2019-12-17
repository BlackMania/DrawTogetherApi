package com.api.logic.authentication;

import com.api.dalcomponent.interfaces.IUserRepository;
import com.api.dalcomponent.model.User;

import javax.inject.Inject;
import javax.naming.AuthenticationException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public class LoginHandler {
    private HashValidator validator;
    private IUserRepository<User> userRepo;
    private TokenHelper tokenHelper;
    private String token;

    @Inject
    public LoginHandler(IUserRepository<User> userRepo) {
        this.userRepo = userRepo;
        this.validator = new HashValidator();
        this.tokenHelper = new TokenHelper();
    }

    private boolean validateLoginAttempt(String username, String password)
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

    public String login(String username, String password) throws AuthenticationException {
        if(validateLoginAttempt(username, password))
        {
            return token;
        } else throw new AuthenticationException("Invalid login credentials");
    }
}
