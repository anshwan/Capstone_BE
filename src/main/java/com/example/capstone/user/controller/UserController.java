package com.example.capstone.user.controller;

import com.example.capstone.auth.jwt.JwtUserPrincipal;
import com.example.capstone.user.entity.AppUser;
import com.example.capstone.user.repository.AppUserRepository;
import com.example.capstone.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AppUserRepository repo;

    /** 로그인 상태 확인 */
    @GetMapping("/me")
    public Map<String, Object> me(@AuthenticationPrincipal JwtUserPrincipal principal) {
        if (principal == null) return Map.of("anonymous", true);
        AppUser user = repo.findById(principal.getId()).orElse(null);
        return Map.of(
                "id", principal.getId(),
                "email", principal.getEmail(),
                "name", principal.getName(),
                "pictureUrl", principal.getPictureUrl(),
                "walletAddress", user != null ? user.getWalletAddress() : null
        );
    }

    /** 지갑 주소 연결 */
    @PatchMapping("/{id}/wallet")
    public Map<String, Object> updateWallet(
            @PathVariable Long id,
            @RequestBody Map<String, String> req,
            @AuthenticationPrincipal JwtUserPrincipal principal
    ) {
        if (principal == null || !principal.getId().equals(id)) {
            throw new RuntimeException("권한 없음");
        }
        String wallet = req.get("walletAddress");
        AppUser updated = userService.updateWalletAddress(id, wallet);
        return Map.of("id", updated.getId(), "walletAddress", updated.getWalletAddress());
    }
}
