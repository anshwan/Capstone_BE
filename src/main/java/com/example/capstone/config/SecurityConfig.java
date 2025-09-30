package com.example.capstone.config;

import com.example.capstone.auth.jwt.JwtAuthFilter;
import com.example.capstone.auth.oauth.GoogleSuccessHandler;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final GoogleSuccessHandler googleSuccessHandler;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter, GoogleSuccessHandler googleSuccessHandler) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.googleSuccessHandler = googleSuccessHandler;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // ✅ 인증 실패 시 리다이렉트 대신 401 반환 (Swagger-friendly)
                .exceptionHandling(e -> e.authenticationEntryPoint((req, res, ex) -> {
                    res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    res.setContentType("application/json;charset=UTF-8");
                    res.getWriter().write("{\"error\":\"unauthorized\"}");
                }))

                .authorizeHttpRequests(auth -> auth
                        // Preflight
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // Swagger (공개 문서)
                        .requestMatchers(
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()

                        // Health & 공개 조회 API (Swagger에서 테스트하는 용도)
                        .requestMatchers("/health").permitAll()
                        .requestMatchers("/api/models/**").permitAll()

                        // OAuth2 로그인/콜백
                        .requestMatchers("/oauth2/**", "/login/oauth2/**").permitAll()

                        // 정적 리소스
                        .requestMatchers("/", "/index.html", "/static/**").permitAll()

                        // 그 외는 인증 필요
                        .anyRequest().authenticated()
                )

                // ✅ 불필요한 기본 로그인 엔트리포인트 제거
                .httpBasic(b -> b.disable())
                .formLogin(f -> f.disable())

                // ✅ OAuth2는 로그인 시점에서만 사용
                .oauth2Login(oauth -> oauth
                        .loginPage("/oauth2/authorization/google")
                        .successHandler(googleSuccessHandler)
                )

                // 로그아웃
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                )

                // ✅ JWT 필터 적용 (UsernamePasswordAuthenticationFilter 전에)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // ✅ CORS: Swagger/FE/배포 도메인 허용 (+ 프리플라이트/헤더 노출)
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // 일반적으로 배포 시에는 특정 도메인만 허용. 개발 중엔 addAllowedOriginPattern("*")도 가능.
        config.setAllowedOrigins(List.of(
                "http://localhost:3000",                  // React Dev
                "http://localhost:8080",                  // 로컬 Swagger
                "https://ai-modelhub-platform.vercel.app",// 배포된 FE
                "https://kau-capstone.duckdns.org"        // 배포된 BE/Swagger
        ));

        config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        config.addAllowedHeader("*");
        config.setExposedHeaders(List.of("Authorization", "Set-Cookie", "Location"));
        config.setAllowCredentials(true);
        config.setMaxAge(3600L); // Preflight 캐시 1시간

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
