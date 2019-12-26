package com.api.logic.authentication;

import org.junit.Assert;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class HashValidationTest {

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
}