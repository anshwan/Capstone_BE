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

@Component
public class JwtIssuer {
    @Value("${JWT_SECRET}")
    private String secret;

    @Value("${JWT_ACCESS_TTL_SECONDS:1800}") // 기본 30분
    private long accessTtl;

    /**
     * Access Token 발급
     * - subject: 사용자 ID (DB PK)
     * - roles: 사용자 권한
     */
    public String issueAccessToken(AppUser u) {
        Instant now = Instant.now();

        return Jwts.builder()
                .setSubject(String.valueOf(u.getId()))          // 사용자 PK
                .setIssuedAt(Date.from(now))                    // 발급 시간
                .setExpiration(Date.from(now.plusSeconds(accessTtl))) // 만료 시간
                .claim("roles", "ROLE_USER")                    // 최소한의 권한만 넣기
                .signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)),
                        SignatureAlgorithm.HS256)
                .compact();
    }
}
