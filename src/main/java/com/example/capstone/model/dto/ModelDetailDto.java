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
public class ModelDetailDto {
    private Long id;                       // 모델 ID
    private String name;                   // 모델 이름
    private String uploader;               // 업로더/회사명
    private String versionName;            // 버전명
    private String modality;               // 모달리티
    private List<String> license;          // 라이선스 배열
    private String releaseDate;            // 출시일
    private String overview;               // 개요 설명
    private Map<String, Object> pricing;   // 가격 정책
    private Map<String, Object> metrics;   // 성능 지표
    private Map<String, Object> technicalSpecs; // 기술 스펙 (contextWindow, maxOutputTokens 등)
    private String compliance;             // 컴플라이언스 정보
    private Object samples;                // 샘플 데이터 (LLM 텍스트, 이미지 프롬프트 등)
    private List<Object> lineage;          // 계보 정보
    private List<Object> releaseNotes;     // 릴리스 노트
    private String cidRoot;                // IPFS CID
    private String checksumRoot;           // 체크섬
    private String onchainTx;              // 온체인 트랜잭션 해시
    private String thumbnail;              // 썸네일 이미지
}
