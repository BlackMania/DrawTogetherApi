package com.api.logic.authentication;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

class TokenHelper {
    private final String SERVER_IP = "localhost";
    private final String PORT = "8080";
    // Key needs to be static since it has to be created only once
    private static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    String issueToken(String username, String clientid) {
        String payload = Jwts.builder()
                .setIssuer("auth-server")
                .setIssuedAt(new Date())
                .setSubject(username)
                .claim("clientid", clientid)
                .claim("serverip", SERVER_IP)
                .claim("port", PORT)
                .signWith(key)
                .compact();
        return payload;
    }

    void verifyToken(String jwsToken)
    {
        Jws<Claims> jws;
        try {
            jws = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(jwsToken);
        }
        catch (JwtException ex) {
            throw ex;
        }
    }
}
