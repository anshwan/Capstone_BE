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
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class GoogleSuccessHandler implements AuthenticationSuccessHandler {

    private final UserService userService;
    private final JwtIssuer jwtIssuer;
    private final ObjectMapper om = new ObjectMapper();

    public GoogleSuccessHandler(UserService userService, JwtIssuer jwtIssuer) {
        this.userService = userService;
        this.jwtIssuer = jwtIssuer;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req,
                                        HttpServletResponse res,
                                        Authentication authentication) throws IOException {
        OAuth2User o = (OAuth2User) authentication.getPrincipal();

        String sub = (String) o.getAttributes().get("sub");     // Google user id
        String email = (String) o.getAttributes().get("email");
        String name = (String) o.getAttributes().get("name");
        String pic = (String) o.getAttributes().get("picture");

        // ✅ DB upsert (없으면 생성, 있으면 업데이트)
        AppUser user = userService.upsertGoogleUser(sub, email, name, pic);

        // ✅ JWT 발급
        String accessToken = jwtIssuer.issueAccessToken(user);

        // ✅ 토큰을 쿠키로 저장하지 않고, 프론트 콜백 페이지로 전달
        String redirectUrl = "https://ai-modelhub-platform.vercel.app/oauth/callback"
                + "?token=" + URLEncoder.encode(accessToken, StandardCharsets.UTF_8)
                + "&userId=" + URLEncoder.encode(user.getId().toString(), StandardCharsets.UTF_8)
                + "&email=" + URLEncoder.encode(email, StandardCharsets.UTF_8)
                + "&name=" + URLEncoder.encode(name, StandardCharsets.UTF_8);

        // 🚀 프론트 콜백 URL로 리다이렉트
        res.sendRedirect(redirectUrl);
    }
}
