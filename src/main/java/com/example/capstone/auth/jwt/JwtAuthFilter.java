package com.example.capstone.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Value("${JWT_SECRET}")
    private String jwtSecret;

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws ServletException, IOException {

        String path = req.getRequestURI();

        // ✅ Swagger, Health, 공개 API는 JWT 검사 패스
        if (path.startsWith("/api/models")
                || path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs")
                || path.equals("/")
                || path.startsWith("/health")) {
            chain.doFilter(req, res);
            return;
        }

        // ✅ JWT 검사
        String header = req.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            try {
                String token = header.substring(7);

                Claims c = Jwts.parserBuilder()
                        .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)))
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                Long userId = Long.valueOf(c.getSubject());

                // roles 클레임 파싱
                Collection<SimpleGrantedAuthority> authorities = parseAuthorities(c.get("roles"));

                // ✅ 최소 Principal (UserId만)
                JwtUserPrincipal principal = new JwtUserPrincipal(userId);

                var auth = new UsernamePasswordAuthenticationToken(
                        principal,
                        null,
                        authorities
                );
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));

                // SecurityContext 에 인증 정보 저장
                org.springframework.security.core.context.SecurityContextHolder
                        .getContext().setAuthentication(auth);

            } catch (Exception e) {
                System.out.println("JWT parsing failed: " + e.getMessage());
            }
        }

        chain.doFilter(req, res);
    }

    private Collection<SimpleGrantedAuthority> parseAuthorities(Object rolesClaim) {
        if (rolesClaim == null) {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }

        if (rolesClaim instanceof String s) {
            return Arrays.stream(s.split(","))
                    .map(String::trim)
                    .filter(x -> !x.isEmpty())
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }

        if (rolesClaim instanceof Collection<?> col) {
            return col.stream()
                    .map(String::valueOf)
                    .map(String::trim)
                    .filter(x -> !x.isEmpty())
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }

        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
