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
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // ✅ Swagger & 문서
                        .requestMatchers(
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()
                        // ✅ 공개 API
                        .requestMatchers("/health", "/api/models/**").permitAll()
                        // ✅ OAuth2 로그인
                        .requestMatchers("/oauth2/**", "/login/oauth2/**").permitAll()
                        // ✅ 나머지 요청은 인증 필요
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth -> oauth.successHandler(googleSuccessHandler))
                .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/"))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // ✅ Swagger + FE + 배포 도메인 CORS 허용
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of(
                "http://localhost:3000",                  // React 개발 환경
                "http://localhost:8080",                  // Swagger UI
                "https://ai-modelhub-platform.vercel.app",// 배포된 FE
                "https://kau-capstone.duckdns.org"        // BE + Swagger
        ));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        config.addAllowedHeader("*");
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
