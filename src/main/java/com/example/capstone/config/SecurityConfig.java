// com/example/capstone/config/SecurityConfig.java
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
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/index.html", "/health", "/static/**").permitAll()
                        .requestMatchers("/oauth2/**", "/login/oauth2/**").permitAll()
                        .requestMatchers("/api/models/**").permitAll()
                        .anyRequest().authenticated()
                )

                .oauth2Login(oauth -> oauth
                        .successHandler(googleSuccessHandler)
                )
                .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/"))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
