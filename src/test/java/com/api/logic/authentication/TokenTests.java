package com.api.logic.authentication;

import io.jsonwebtoken.JwtException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Base64;
import java.util.UUID;

public class TokenTests {

    TokenHelper helper = new TokenHelper();

    @Test
    public void validateCorrectToken()
    {
        UUID uuid = UUID.randomUUID();
        String token = helper.issueToken("testuser", uuid.toString());

        helper.verifyToken(token);
    }

    @Test
    public void validateIncorrectToken()
    {
        UUID uuid = UUID.randomUUID();
        String token = helper.issueToken("testuser", uuid.toString());

        String[] tokenParts = token.split("\\.");
        byte[] decodedPayload = Base64.getDecoder().decode(tokenParts[1].getBytes());

        String adjustedPayload = new String(decodedPayload);
        adjustedPayload.replace("testuser", "anotherTestUser");
        String encodedAdjustedPayload = Base64.getEncoder().encodeToString(adjustedPayload.getBytes());

        String adjustedToken = tokenParts[0] + "." + encodedAdjustedPayload + "." + tokenParts[2];

        Assert.assertThrows(JwtException.class, () -> helper.verifyToken(adjustedToken));
    }
}
