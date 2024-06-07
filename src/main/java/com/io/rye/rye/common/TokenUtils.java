package com.io.rye.rye.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class TokenUtils {

    @Value("${jwt.token.key}")
    private String key;

    public String getIdFromToken(HttpHeaders headers) {
        String token = headers.get("Authorization").get(0);
        String jwt = token.replace("Bearer ", "");
        Claims claims = Jwts.parser().setSigningKey(key).build().parseSignedClaims(jwt).getPayload();
        return String.valueOf(claims.get("id"));
    }
}