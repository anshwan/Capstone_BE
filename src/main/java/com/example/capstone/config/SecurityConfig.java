package com.example.capstone.config;

import com.example.capstone.auth.jwt.JwtAuthFilter;
import com.example.capstone.auth.oauth.GoogleSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // ✅ CORS 설정
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Swagger (공개 문서)
                        .requestMatchers(
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()
                        // Health Check & 공개 조회 API
                        .requestMatchers("/health", "/api/models/**").permitAll()
                        // Google OAuth2 로그인 콜백
                        .requestMatchers("/oauth2/**", "/login/oauth2/**").permitAll()
                        // 나머지는 인증 필요
                        .anyRequest().authenticated()
                )
                // 소셜 로그인 성공 핸들러
                .oauth2Login(oauth -> oauth.successHandler(googleSuccessHandler))
                // 로그아웃
                .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/"))
                // JWT 필터 적용
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // ✅ Swagger + Frontend + 배포 도메인 CORS 허용
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of(
                "http://localhost:3000",                 // React 개발 환경
                "http://localhost:8080",                 // Swagger UI (로컬)
                "https://ai-modelhub-platform.vercel.app", // 배포된 FE
                "https://kau-capstone.duckdns.org"       // 배포된 BE (Swagger)
        ));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        config.addAllowedHeader("*"); // 모든 헤더 허용
        config.setAllowCredentials(true); // 쿠키, Authorization 헤더 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
