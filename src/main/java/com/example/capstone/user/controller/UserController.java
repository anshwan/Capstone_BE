package com.example.capstone.user.controller;

import com.example.capstone.auth.jwt.JwtUserPrincipal;
import com.example.capstone.user.entity.AppUser;
import com.example.capstone.user.repository.AppUserRepository;
import com.example.capstone.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User API", description = "사용자 정보 및 지갑 주소 관리 API")
public class UserController {

    private final UserService userService;
    private final AppUserRepository repo;

    /**
     * 로그인 상태 확인
     */
    @GetMapping("/me")
    @Operation(
            summary = "현재 로그인한 사용자 정보 조회",
            description = "JWT 인증 토큰을 이용해 현재 로그인한 사용자의 정보를 반환합니다. " +
                    "로그인하지 않은 경우 `{ \"anonymous\": true }` 를 반환합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "사용자 정보 조회 성공"),
                    @ApiResponse(responseCode = "401", description = "인증 실패 (토큰 없음/만료)")
            }
    )
    public Map<String, Object> me(
            @Parameter(hidden = true) @AuthenticationPrincipal JwtUserPrincipal principal
    ) {
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

    /**
     * 지갑 주소 연결
     */
    @PatchMapping("/{id}/wallet")
    @Operation(
            summary = "사용자 지갑 주소 등록/수정",
            description = "사용자가 자신의 지갑 주소를 등록하거나 업데이트합니다. " +
                    "경로의 {id} 는 반드시 현재 로그인한 사용자 ID와 일치해야 합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "지갑 주소 업데이트 성공"),
                    @ApiResponse(responseCode = "403", description = "권한 없음 (본인만 수정 가능)"),
                    @ApiResponse(responseCode = "401", description = "인증 실패")
            }
    )
    public Map<String, Object> updateWallet(
            @Parameter(description = "수정할 사용자 ID", example = "1") @PathVariable Long id,
            @RequestBody Map<String, String> req,
            @Parameter(hidden = true) @AuthenticationPrincipal JwtUserPrincipal principal
    ) {
        if (principal == null || !principal.getId().equals(id)) {
            throw new RuntimeException("권한 없음");
        }
        String wallet = req.get("walletAddress");
        AppUser updated = userService.updateWalletAddress(id, wallet);
        return Map.of("id", updated.getId(), "walletAddress", updated.getWalletAddress());
    }
}
