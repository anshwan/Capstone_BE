package com.example.capstone.model.controller;


import com.example.capstone.model.dto.ModelDetailResponse;
import com.example.capstone.model.dto.ModelListResponse;
import com.example.capstone.model.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/models")
@RequiredArgsConstructor
public class ModelController {
    private final ModelService modelService;

    /** 전체 모델 조회 */
    @GetMapping
    public List<ModelListResponse> getAllModels() {
        return modelService.getAllModels();
    }

    /** 특정 모델 상세 조회 */
    @GetMapping(params = "id")
    public ModelDetailResponse getModelDetail(@RequestParam Long id) {
        return modelService.getModelDetail(id);
    }
}
