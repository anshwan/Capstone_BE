package com.example.capstone.auth.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.security.Principal;

@Getter
@AllArgsConstructor
public class JwtUserPrincipal implements Principal {
    private final Long id;

    @Override
    public String getName() {
        // Spring Security Authentication.getName() 이 userId 반환
        return String.valueOf(id);
    }
}
