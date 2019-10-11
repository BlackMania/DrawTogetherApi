package com.api.logincomponent.tokenlogic;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

public class TokenHelper {
    private static final String SERVER_IP = "localhost";
    private static final String PORT = "8080";
    private static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String issueToken(String login) {
        String payload = Jwts.builder()
                .setIssuer("auth-server")
                .setIssuedAt(new Date())
                .setSubject(login)
                .claim("serverip", SERVER_IP)
                .claim("port", PORT)
                .signWith(key)
                .compact();
        return payload;
    }

    public void verifyToken(String jwsToken)
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
