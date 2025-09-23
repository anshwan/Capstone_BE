package com.example.capstone.model.controller;

import com.example.capstone.model.dto.ModelUploadRequest;
import com.example.capstone.model.service.ModelUploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/models")
@RequiredArgsConstructor
@Tag(name = "Model Upload API", description = "AI 모델 업로드 관련 API")
public class ModelUploadController {

    private final ModelUploadService modelUploadService;

    @PostMapping("/upload")
    @Operation(
            summary = "모델 업로드",
            description = """
                    새로운 AI 모델을 업로드합니다.  
                    요청 시 모델 이름, 버전, 설명 등 메타데이터를 JSON 형식으로 전달해야 합니다.
                    """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "업로드 성공"),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청 (필수 값 누락 등)"),
                    @ApiResponse(responseCode = "401", description = "인증 실패 (JWT 필요)")
            }
    )
    public ResponseEntity<?> upload(@RequestBody ModelUploadRequest request) {
        return ResponseEntity.ok(modelUploadService.uploadModel(request));
    }
}
