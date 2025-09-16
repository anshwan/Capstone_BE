package com.example.capstone.auth.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.security.Principal;

@Getter
@AllArgsConstructor
public class JwtUserPrincipal implements Principal {
    private final Long id;
    private final String email;
    private final String name;
    private final String pictureUrl;

    @Override
    public String getName() {
        // Spring Security Authentication.getName() 이 userId 가 되도록
        return String.valueOf(id);
    }
}
