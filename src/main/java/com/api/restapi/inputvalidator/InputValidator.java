package com.api.restapi.inputvalidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {
    private static final String emailRegex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    private static final String passwordRegex = "((?=.*[a-z])(?=.*d)(?=.*[A-Z]).{6,100})";

    public static Boolean validateEmailInput(String email)
    {
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        Boolean t = matcher.matches();
        return  matcher.matches();
    }

    public static  Boolean validatePasswordInput(String password)
    {
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        Boolean t = matcher.matches();
        return matcher.matches();
    }
}
