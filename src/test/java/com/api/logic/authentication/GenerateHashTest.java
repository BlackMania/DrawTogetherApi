package com.api.logic.authentication;

import org.junit.Assert;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class GenerateHashTest {
    @Test
    public void generateHash()
    {
        PasswordHasher hasher = new PasswordHasher();
        String generatedHash = "";
        try {
            generatedHash = hasher.generateStrongPassword("password1!!");
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        String[] hashPart = generatedHash.split(":");

        Assert.assertEquals(hashPart.length, 3);
    }
}
