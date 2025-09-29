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
import jakarta.servlet.http.Cookie;

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

        // 🍪 HttpOnly 쿠키에 토큰 저장
        Cookie cookie = new Cookie("accessToken", access);
        cookie.setHttpOnly(true);   // JS에서 접근 불가
        cookie.setSecure(true);     // HTTPS에서만 전송 (개발환경이면 false)
        cookie.setPath("/");        // 모든 경로에서 사용 가능
        cookie.setMaxAge(60 * 60);  // 1시간

        res.addCookie(cookie);

        // ✅ 로그인 후 프론트 페이지로 리다이렉트
        res.sendRedirect("https://ai-modelhub-platform.vercel.app/home");
    }


}
