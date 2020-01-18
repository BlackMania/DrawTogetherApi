package com.api.restapi.inputvalidator;

import org.junit.Assert;
import org.junit.Test;

public class InputEmailTest {
    @Test
    public void testValidEmailAddress() {
        String email = "test@email.com";

        boolean success = InputValidator.validateEmailInput(email);

        Assert.assertTrue(success);
    }

    @Test
    public void testInvalidEmailAddress() {
        String email = "qwe233qwese";

        boolean success = InputValidator.validateEmailInput(email);

        Assert.assertFalse(success);
    }
}
