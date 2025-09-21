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

                // ✅ sub 값 유연 처리 (숫자면 Long, 아니면 -1)
                String subject = c.getSubject();
                Long userId = null;
                try {
                    userId = Long.valueOf(subject);
                } catch (NumberFormatException e) {
                    userId = -1L; // 이메일 같은 경우는 -1 처리
                }

                String email = c.get("email", String.class);
                String name  = c.get("name", String.class);
                String pic   = c.get("picture", String.class);

                // roles 클레임 파싱
                Collection<SimpleGrantedAuthority> authorities = parseAuthorities(c.get("roles"));

                JwtUserPrincipal principal = new JwtUserPrincipal(
                        userId,
                        email,
                        name,
                        pic
                );

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
                // 토큰 검증 실패 → 로그 남기고 익명으로 통과
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
