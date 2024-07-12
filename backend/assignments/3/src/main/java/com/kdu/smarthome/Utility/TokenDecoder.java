package com.kdu.smarthome.Utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

/**
 * Utility class for decoding JWT tokens.
 */
@Component
public class TokenDecoder {

    @Value("${jwt.secret}")
    private String secretKey;

    /**
     * Decodes a JWT token and retrieves the username from its claims.
     *
     * @param token The JWT token to be decoded.
     * @return The username extracted from the token.
     */
    public String decodeToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return String.valueOf(claims.get("username"));
    }
}
