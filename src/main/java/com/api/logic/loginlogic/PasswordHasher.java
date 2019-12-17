package com.api.logic.loginlogic;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class PasswordHasher {
    private byte[] salt;
    private final int iterations = 65536;

    public PasswordHasher() {
        generateSalt();
    }

    private void generateSalt()
    {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[64];
        random.nextBytes(salt);
        this.salt = salt;
    }



}
