package com.api.logic.authentication;


import com.api.dalcomponent.InMemTableUtils;
import com.api.dalcomponent.interfaces.IUserRepository;
import com.api.dalcomponent.model.User;
import com.api.dalcomponent.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class AuthHandlerTests {
    private IUserRepository<User> userRepo;

    public AuthHandlerTests() {
        try {
            InMemTableUtils.generateInMemDatabase();
            userRepo = new UserRepository<User>(InMemTableUtils.getContext());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void validateValidLoginAttempt() {
        AuthHandler handler = new AuthHandler(userRepo);

        boolean result = handler.validateLoginAttempt("testuser", "password");

        Assert.assertTrue(result);
    }

    @Test
    public void validateInvalidLoginAttempt()
    {
        AuthHandler handler = new AuthHandler(userRepo);

        boolean result = handler.validateLoginAttempt("testuser", "na");

        Assert.assertFalse(result);
    }

    @Test
    public void tokenIsNotNullWhenLoginAttemptIsValid()
    {
        AuthHandler handler = new AuthHandler(userRepo);

        handler.validateLoginAttempt("testuser", "password");

        Assert.assertNotNull(handler.getToken());
    }

    @Test
    public void tokenIsNullWhenInvalidLoginAttempt()
    {
        AuthHandler handler = new AuthHandler(userRepo);

        handler.validateLoginAttempt("testuser", "na");

        Assert.assertNull(handler.getToken());
    }
}
