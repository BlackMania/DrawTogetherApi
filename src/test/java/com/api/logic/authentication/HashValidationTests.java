package com.api.logic.authentication;

import org.junit.Assert;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

public class HashValidationTests {

    @Test
    public void testAreHashesTheSame() throws InvalidKeySpecException, NoSuchAlgorithmException {
        HashValidator validator = new HashValidator();
        PasswordHasher hasher = new PasswordHasher();
        String  storedHash = hasher.generateStrongPassword("Password1!");

        Assert.assertTrue(validator.validatePassword("Password1!", storedHash));
    }

    @Test
    public void testPasswordsAreNotTheSame() throws InvalidKeySpecException, NoSuchAlgorithmException {
        HashValidator validator = new HashValidator();
        PasswordHasher hasher = new PasswordHasher();

        String storedHash = hasher.generateStrongPassword("Password1!");

        Assert.assertFalse(validator.validatePassword("anotherpassword", storedHash));
    }

    @Test
    public void testPasswordGeneratedButWithAdjustedIteratorCount() throws InvalidKeySpecException, NoSuchAlgorithmException {
        HashValidator validator = new HashValidator();
        PasswordHasher hasher = new PasswordHasher();

        String storedHash = hasher.generateStrongPassword("Password1!");

        // Value is still same generated since the iteration count isn't changed.
        Assert.assertTrue(validator.validatePassword("Password1!", storedHash));

        String[] splitHash = storedHash.split(":");

        splitHash[0] = "208234";

        storedHash = splitHash[0] + ":" + splitHash[1] + ":" + splitHash[2];

        // The iteration count is changed so the generated hash is not the same as the stored hash anymore. Since it took more or less iterations to generate the outcome of the hash.
        Assert.assertFalse(validator.validatePassword("Password1!", storedHash));
    }
}