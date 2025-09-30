package com.example.capstone.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModelSummaryDto {
    private Long id;                       // 모델 ID
    private String name;                   // 모델 이름
    private String uploader;               // 업로더/회사명
    private String versionName;            // 버전명
    private String modality;               // 모달리티 (LLM, image-generation 등)
    private List<String> license;          // 라이선스 배열
    private String releaseDate;            // 출시일
    private Map<String, Object> pricing;   // 가격 정책 (research/standard/enterprise 블록)
    private Map<String, Object> metrics;   // 성능 지표
    private String thumbnail;              // 썸네일 이미지
}
