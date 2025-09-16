package com.example.capstone.user.controller;

import com.example.capstone.auth.jwt.JwtUserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MeController {

    @GetMapping("/health")
    public Map<String,Object> health(){
        return Map.of("status","ok");
    }

    @GetMapping("/me")
    public Map<String,Object> me(@AuthenticationPrincipal JwtUserPrincipal principal){
        if (principal == null) return Map.of("anonymous", true);
        return Map.of(
                "id", principal.getId(),
                "email", principal.getEmail(),
                "name", principal.getName(),
                "pictureUrl", principal.getPictureUrl()
        );
    }
}
