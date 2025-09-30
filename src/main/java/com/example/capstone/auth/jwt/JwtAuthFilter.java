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

                JwtUserPrincipal principal = new JwtUserPrincipal(userId);

                var auth = new UsernamePasswordAuthenticationToken(
                        principal,
                        null,
                        authorities
                );
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));

                org.springframework.security.core.context.SecurityContextHolder
                        .getContext().setAuthentication(auth);

            } catch (Exception e) {
                System.out.println("JWT parsing failed: " + e.getMessage());
            }
        }

        chain.doFilter(req, res);
    }

    /**
     * ✅ 공개 API는 JWT 검사 대상에서 제외
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();

        return path.startsWith("/api/models")   // 공개 모델 조회 API
                || path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs")
                || path.startsWith("/health")
                || path.equals("/")
                || path.startsWith("/oauth2")
                || path.startsWith("/login/oauth2")
                || path.startsWith("/error");
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
