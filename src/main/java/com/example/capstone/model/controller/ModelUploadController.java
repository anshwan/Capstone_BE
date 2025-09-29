// src/main/java/com/example/capstone/model/controller/ModelUploadController.java
package com.example.capstone.model.controller;

import com.example.capstone.auth.jwt.JwtUserPrincipal;
import com.example.capstone.model.dto.ModelUploadRequest;
import com.example.capstone.model.dto.ModelUploadResponse;
import com.example.capstone.model.service.ModelUploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/models")
@RequiredArgsConstructor
@Tag(name = "Model Upload API", description = "AI 모델 업로드 관련 API")
public class ModelUploadController {

    private final ModelUploadService modelUploadService;

    @PostMapping("/upload")
    @Operation(
            summary = "모델 업로드 & 온체인 등록",
            description = """
                - 프론트는 먼저 IPFS 노드서버에서 CID/암호화 등을 완료한 뒤,
                - 본 API에 메타데이터 + (지갑주소, 서명) + ipfsCid + 가격/권리정보 등을 전달.
                - 메인 백엔드는 온체인 백엔드에 register_model을 호출하고,
                  성공(or 실패시 더미TX 대체) 후 DB에 모델/버전 정보를 저장합니다.
                """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "업로드 성공"),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청(검증 실패 등)"),
                    @ApiResponse(responseCode = "401", description = "인증 실패(JWT 필요)")
            }
    )
    public ResponseEntity<ModelUploadResponse> upload(
            @AuthenticationPrincipal JwtUserPrincipal user,
            @Valid @RequestBody ModelUploadRequest request
    ) {
        return ResponseEntity.ok(modelUploadService.uploadModel(user, request));
    }
}
