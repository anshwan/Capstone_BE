package com.example.capstone.model.controller;

import com.example.capstone.model.dto.ModelDetailResponse;
import com.example.capstone.model.dto.ModelListResponse;
import com.example.capstone.model.dto.ModelUploadRequest;
import com.example.capstone.model.service.ModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models")
@RequiredArgsConstructor
@Tag(name = "Model API", description = "모델 조회 API")
public class ModelController {
    private final ModelService modelService;

    /** 전체 모델 조회 또는 단일 모델 조회 (쿼리 파라미터 지원) */
    @GetMapping
    @Operation(summary = "모델 조회", description = "모델 ID가 없으면 전체 목록, 있으면 상세 정보를 반환합니다.")
    public ResponseEntity<?> getModels(@RequestParam(value = "id", required = false) Long id) {
        if (id != null) {
            ModelDetailResponse detail = modelService.getModelDetail(id);
            return ResponseEntity.ok(detail);
        } else {
            List<ModelListResponse> list = modelService.getAllModels();
            return ResponseEntity.ok(list);
        }
    }

    /** 기존 PathVariable 방식도 그대로 유지 (RESTful 접근) */
    @GetMapping("/{id}")
    @Operation(summary = "모델 상세 조회 (PathVariable)", description = "모델 ID를 경로 변수로 전달하여 상세 정보를 조회합니다.")
    public ResponseEntity<ModelDetailResponse> getModelDetail(@PathVariable Long id) {
        return ResponseEntity.ok(modelService.getModelDetail(id));
    }

    /** 모델 등록 */
    @PostMapping("/upload")
    @Operation(summary = "모델 등록", description = "새 모델을 업로드합니다.")
    public ResponseEntity<?> uploadModel(@RequestBody @Valid ModelUploadRequest request) {
        Long id = modelService.uploadModel(request);
        return ResponseEntity.ok(id);
    }
}
