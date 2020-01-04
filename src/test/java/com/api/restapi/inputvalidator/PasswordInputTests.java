package com.api.restapi.inputvalidator;

import org.junit.Assert;
import org.junit.Test;

public class PasswordInputTests {

    // Passwords requires a capital and a lower case character and need to be 6-100 characters long

    @Test
    public void testValidPasswordWith1CapitalAnd1LowerCase() {
        String password = "Password";

        boolean success = InputValidator.validatePasswordInput(password);

        Assert.assertTrue(success);
    }

    @Test
    public void testInvalidPasswordWhichNotPassesTheRequirements() {
        String password = "password";

        boolean success = InputValidator.validatePasswordInput(password);

        Assert.assertFalse(success);
    }

    @Test
    public void testPasswordTooShort() {
        String password = "pard";

        boolean success = InputValidator.validatePasswordInput(password);

        Assert.assertFalse(success);
    }
}
