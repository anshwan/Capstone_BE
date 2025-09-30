package com.example.capstone.model.controller;

import com.example.capstone.model.dto.ModelDetailDto;
import com.example.capstone.model.dto.ModelSummaryDto;
import com.example.capstone.model.service.ModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models")
@RequiredArgsConstructor
@Tag(name = "Model API", description = "AI 모델 정보 조회 API (목록/상세)")
public class ModelController {

    private final ModelService modelService;

    /**
     * 전체 모델 조회
     * GET /api/models
     */
    @GetMapping
    @Operation(
            summary = "모델 전체 조회",
            description = """
                    등록된 모든 AI 모델의 기본 정보를 조회합니다.  
                    반환 값은 모델 ID, 이름, 버전, 업로더, 모달리티, 라이선스, 가격, 메트릭스, 썸네일 등의 요약 정보입니다.
                    """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "모델 목록 조회 성공")
            }
    )
    public ResponseEntity<List<ModelSummaryDto>> getAllModels() {
        return ResponseEntity.ok(modelService.getAllModels());
    }

    /**
     * 특정 모델 상세 조회
     * GET /api/models/{id}
     */
    @GetMapping("/{id}")
    @Operation(
            summary = "특정 모델 상세 조회",
            description = """
                    특정 모델의 상세 정보를 조회합니다.  
                    모델 ID를 경로 변수(`id`)로 전달해야 합니다.  
                    반환 값은 개요, 가격 정책, 성능 지표, 샘플, 계보, 릴리스 노트 등 상세 정보입니다.
                    """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "모델 상세 조회 성공"),
                    @ApiResponse(responseCode = "404", description = "해당 ID의 모델을 찾을 수 없음")
            }
    )
    public ResponseEntity<ModelDetailDto> getModelDetail(@PathVariable Long id) {
        return ResponseEntity.ok(modelService.getModelDetail(id));
    }
}
