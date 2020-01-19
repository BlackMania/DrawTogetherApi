package com.api.logic.authentication;


import com.api.dalcomponent.InMemTableUtils;
import com.api.dalcomponent.interfaces.IUserRepository;
import com.api.dalcomponent.model.User;
import com.api.dalcomponent.repository.UserRepository;
import com.auth0.jwt.exceptions.JWTVerificationException;
import io.jsonwebtoken.MalformedJwtException;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

public class AuthHandlerTest {
    private IUserRepository<User> userRepo;

    public AuthHandlerTest() {
        userRepo = new UserRepository<User>(InMemTableUtils.getContext());
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
    public void validateLoginAttemptWithNoRecognizeedUsername()
    {
        AuthHandler handler = new AuthHandler(userRepo);

        boolean result = handler.validateLoginAttempt("test", "password");

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


    @Test
    public void validateValidTokenAuthenticationAttempt()
    {
        AuthHandler handler = new AuthHandler(userRepo);
        String token;

        handler.validateLoginAttempt("testuser", "password");
        token = handler.getToken();

        Assert.assertTrue(handler.validateTokenAuthAttempt(token));
    }

    @Test
    public void validateInvalidTokenAuthenticationAttempt()
    {
        AuthHandler handler = new AuthHandler(userRepo);
        TokenHelper helper = new TokenHelper();
        UUID uuid = UUID.randomUUID();
        String token = helper.issueToken("testuser", uuid.toString());

        String[] tokenParts = token.split("\\.");
        byte[] decodedPayload = Base64.getDecoder().decode(tokenParts[1].getBytes());

        String adjustedPayload = new String(decodedPayload);
        adjustedPayload.replace("testuser", "anotherTestUser");
        String encodedAdjustedPayload = Base64.getEncoder().encodeToString(adjustedPayload.getBytes());

        String adjustedToken = tokenParts[0] + "." + encodedAdjustedPayload + "." + tokenParts[2];

        Assert.assertFalse(handler.validateTokenAuthAttempt(adjustedToken));
    }
}
