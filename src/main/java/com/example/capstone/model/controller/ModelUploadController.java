package com.example.capstone.model.controller;

import com.example.capstone.model.dto.ModelUploadRequest;
import com.example.capstone.model.service.ModelUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/models")
@RequiredArgsConstructor
public class ModelUploadController {
    private final ModelUploadService modelUploadService;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestBody ModelUploadRequest request) {
        return ResponseEntity.ok(modelUploadService.uploadModel(request));
    }
}
