package com.example.capstone.model.controller;

import com.example.capstone.model.dto.ModelDetailResponse;
import com.example.capstone.model.dto.ModelListResponse;
import com.example.capstone.model.service.ModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models")
@RequiredArgsConstructor
@Tag(name = "Model API", description = "모델 조회 API")
public class ModelController {
    private final ModelService modelService;

    /** 전체 모델 조회 */
    @GetMapping
    @Operation(summary = "전체 모델 조회", description = "등록된 모든 모델을 요약 정보로 조회합니다.")
    public List<ModelListResponse> getAllModels() {
        return modelService.getAllModels();
    }

    /** 특정 모델 상세 조회 */
    @GetMapping(params = "id")
    @Operation(summary = "모델 상세 조회", description = "모델 ID를 기준으로 상세 정보를 조회합니다.")
    public ModelDetailResponse getModelDetail(@RequestParam Long id) {
        return modelService.getModelDetail(id);
    }
}
