package com.example.capstone.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class ModelDetailDto {
    private Long id;                 // 모델 ID
    private String name;             // 모델 이름
    private String versionName;      // 버전 이름
    private String status;           // 상태 (예: PUBLISHED)
    private String uploader;         // 등록자
    private String modality;         // 모달리티 이름
    private String license;          // 라이선스 이름
    private String currency;         // 화폐 단위
    private BigDecimal priceResearch;   // 연구용 가격
    private BigDecimal priceStandard;   // 표준 가격
    private BigDecimal priceEnterprise; // 엔터프라이즈 가격
    private String overview;            // 모델 개요
    private Map<String, Object> metrics;   // ✅ String → Map
    private List<Object> samples;          // ✅ String → List
    private List<Object> lineage;         // 계보/학습 정보 (JSON)
    private String releaseNotes;        // 릴리즈 노트
    private LocalDate releaseDate;      // 릴리즈 날짜
    private String cidRoot;             // IPFS CID
    private String checksumRoot;        // 체크섬
    private String onchainTx;           // 온체인 트랜잭션
}
