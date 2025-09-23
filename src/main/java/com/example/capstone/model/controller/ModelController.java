package com.example.capstone.model.controller;

import com.example.capstone.model.dto.ModelCreateRequest;
import com.example.capstone.model.dto.ModelCreatedResponse;
import com.example.capstone.model.dto.ModelDetailDto;
import com.example.capstone.model.dto.ModelSummaryDto;
import com.example.capstone.model.service.ModelService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/models")
@RequiredArgsConstructor
@Tag(name = "모델 조회", description = "모델 정보 조회 API (목록/상세)")
public class ModelController {

    private final ModelService modelService;

    /**
     * 전체 모델 조회
     * GET /api/models
     */
    @GetMapping
    @Operation(summary = "모델 전체 조회")
    public ResponseEntity<List<ModelSummaryDto>> getAllModels() {
        List<ModelSummaryDto> models = modelService.getAllModels();
        return ResponseEntity.ok(models);
    }

    /**
     * 특정 모델 상세 조회
     * GET /api/models/{id}
     */
    @GetMapping("/{id}")
    @Operation(summary = "특정 모델 조회")
    public ResponseEntity<ModelDetailDto> getModelDetail(@PathVariable Long id) {
        ModelDetailDto modelDetail = modelService.getModelDetail(id);
        return ResponseEntity.ok(modelDetail);
    }
}