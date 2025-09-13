package com.example.capstone.auth.oauth;

import com.example.capstone.auth.jwt.JwtIssuer;
import com.example.capstone.user.entity.AppUser;
import com.example.capstone.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Map;

@Component
public class GoogleSuccessHandler implements AuthenticationSuccessHandler {
    private final UserService userService;
    private final JwtIssuer jwtIssuer;
    private final ObjectMapper om = new ObjectMapper();

    public GoogleSuccessHandler(UserService userService, JwtIssuer jwtIssuer) {
        this.userService = userService; this.jwtIssuer = jwtIssuer;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res,
                                        Authentication authentication) throws IOException {
        OAuth2User o = (OAuth2User) authentication.getPrincipal();
        String sub = (String) o.getAttributes().get("sub");
        String email = (String) o.getAttributes().get("email");
        String name = (String) o.getAttributes().get("name");
        String pic = (String) o.getAttributes().get("picture");

        AppUser user = userService.upsertGoogleUser(sub, email, name, pic);
        String access = jwtIssuer.issueAccessToken(user);

        res.setContentType("application/json");
        res.getWriter().write(om.writeValueAsString(Map.of(
                "accessToken", access, "tokenType", "Bearer",
                "user", Map.of("id", user.getId(), "email", user.getEmail(), "name", user.getName(), "pictureUrl", user.getPictureUrl())
        )));
        res.getWriter().flush();
    }
}
