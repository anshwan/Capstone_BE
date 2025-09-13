package com.example.capstone.auth.jwt;

import com.example.capstone.user.entity.AppUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Component
public class JwtIssuer {
    @Value("${JWT_SECRET}") private String secret;
    @Value("${JWT_ACCESS_TTL_SECONDS:1800}") private long accessTtl; // 30분

    public String issueAccessToken(AppUser u) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setSubject(String.valueOf(u.getId()))
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(accessTtl)))
                .addClaims(Map.of(
                        "email", u.getEmail(),
                        "name", u.getName(),
                        "picture", u.getPictureUrl(),
                        "roles", "ROLE_USER"
                ))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .compact();
    }
}
