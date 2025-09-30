package com.example.capstone.model.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
public class ModelUploadRequest {

    /* 기본 메타 */
    @NotBlank private String modelName;      // 모델 이름
    @NotBlank private String versionName;    // 버전명
    @NotBlank private String modalityCode;   // LLM | image-generation | audio | multimodal
    @NotEmpty private List<String> license;  // ["research", "commercial"] 등 배열

    /* 가격 정책 - JSON 그대로 저장 */
    @NotNull private Map<String, Object> pricing;
    // 예시:
    // {
    //   "research": { "price": 0, "description": "연구용", "billingType": "free" },
    //   "standard": { "price": 20, "description": "표준", "billingType": "monthly_subscription", "monthlyTokenLimit": 1000000 }
    // }

    @Size(max = 10)
    private String currency = "USDC";

    /* 상세 메타 */
    @Size(max = 2000) private String overview;
    private LocalDate releaseDate;

    /* JSON/배열 메타 */
    private Map<String, Object> metrics;         // 성능 지표 (LLM: MMLU 등, image: FID 등)
    private Object samples;                      // 샘플 (문자열 or {prompt, outputImage} 등)
    private Object lineage;                      // 부모모델 계보 (단일 or 배열)
    private Map<String, Object> technicalSpecs;  // 기술 스펙 (contextWindow, maxOutputTokens 등)
    private List<Object> releaseNotes;           // 릴리스 노트 배열
    private String compliance;                   // 컴플라이언스 정보
    private String thumbnail;                    // 썸네일 URL

    /* IPFS */
    @NotBlank private String cidRoot;
    @NotBlank private String checksumRoot;

    /* 온체인 관련 입력 */
    @NotBlank private String developerWallet;   // base58 pubkey
    private String developerSignature;          // optional

    /** 온체인 가격 (Lamports 단위, string u64) */
    @Pattern(regexp = "^[0-9]+$", message = "priceLamports must be a numeric string")
    @NotBlank
    private String priceLamports;

    /** 로열티 (bps 단위, 예: 250 = 2.5%) */
    @Min(0) @Max(10000)
    private Integer royaltyBps = 250;

    /** 부모 모델 (명시적으로 단일 문자열 지정 가능, 없으면 lineage 사용) */
    private Object parentModel;
}
