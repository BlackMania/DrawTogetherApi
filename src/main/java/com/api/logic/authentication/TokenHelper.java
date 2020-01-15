package com.api.logic.authentication;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

class TokenHelper {
    private final String SERVER_IP = "localhost";
    private final String PORT = "8080";
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

    void verifyToken(String jwsToken){
        Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwsToken);
    }
}
