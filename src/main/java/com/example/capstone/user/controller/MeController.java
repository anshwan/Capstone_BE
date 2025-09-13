package com.example.capstone.user.controller;

import com.example.capstone.auth.jwt.JwtAuthFilter.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class MeController {
    @GetMapping("/health")
    public Map<String,Object> health(){ return Map.of("status","ok"); }

    @GetMapping("/me")
    public Map<String,Object> me(Authentication auth){
        if (auth==null || !(auth.getPrincipal() instanceof UserPrincipal p))
            return Map.of("anonymous", true);
        return Map.of("id", p.id(), "email", p.email(), "name", p.name());
    }
}
