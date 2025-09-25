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
@Tag(name = "Model API", description = "AI 모델 정보 조회 API (목록/상세/필터링)")
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
                    반환 값은 모델 ID, 이름, 버전, 작성자 등의 요약 정보입니다.
                    """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "모델 목록 조회 성공")
            }
    )
    public ResponseEntity<List<ModelSummaryDto>> getAllModels() {
        List<ModelSummaryDto> models = modelService.getAllModels();
        return ResponseEntity.ok(models);
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
                    반환 값은 모델 설명, 성능 지표, 업로드된 샘플 등 상세 정보입니다.
                    """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "모델 상세 조회 성공"),
                    @ApiResponse(responseCode = "404", description = "해당 ID의 모델을 찾을 수 없음")
            }
    )
    public ResponseEntity<ModelDetailDto> getModelDetail(@PathVariable Long id) {
        ModelDetailDto modelDetail = modelService.getModelDetail(id);
        return ResponseEntity.ok(modelDetail);
    }

    /**
     * 모델 필터링 조회
     * GET /api/models/filter
     */
    @GetMapping("/filter")
    @Operation(
            summary = "모델 필터링 조회",
            description = """
                    모달리티, 라이선스, 가격 범위, 최소 성능 조건에 따라 모델을 필터링합니다.  
                    - modality: LLM, VLM, 이미지 등  
                    - license: 연구용, 상업용, 온프렘 등  
                    - maxPrice: 최대 가격 (기본값 1000)  
                    - minPerformance: 최소 성능 (기본값 0)
                    """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "필터링된 모델 목록 반환")
            }
    )
    public ResponseEntity<List<ModelSummaryDto>> filterModels(
            @RequestParam(required = false) String modality,
            @RequestParam(required = false) String license,
            @RequestParam(required = false, defaultValue = "1000") Double maxPrice,
            @RequestParam(required = false, defaultValue = "0") Double minPerformance
    ) {
        List<ModelSummaryDto> models = modelService.filterModels(modality, license, maxPrice, minPerformance);
        return ResponseEntity.ok(models);
    }
}
