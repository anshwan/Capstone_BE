// src/main/java/com/example/capstone/model/dto/ModelUploadRequest.java
package com.example.capstone.model.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
public class ModelUploadRequest {

    /* 기본 메타 */
    @NotBlank private String modelName;
    @NotBlank private String versionName;
    @NotBlank private String modalityCode;   // LLM/VLM/IMAGE...
    @NotBlank private String licenseCode;    // RESEARCH/COMMERCIAL/ONPREM/FT...

    /* 가격(서비스 요금제별 - DB 저장용) */
    @NotNull @DecimalMin("0.0") private Double priceResearch;
    @NotNull @DecimalMin("0.0") private Double priceStandard;
    @NotNull @DecimalMin("0.0") private Double priceEnterprise;
    @Size(max = 10) private String currency = "USDC";

    /* 상세 메타 */
    @Size(max = 2000) private String overview;
    @Size(max = 4000) private String releaseNotes;
    private LocalDate releaseDate;

    /* 자유 JSON/배열 메타 */
    private Map<String, Object> storage;  // 저장/무결성 정보 (예: s3/ipfs/암호화 알고리즘 등)
    private List<Object> metrics;         // 성능 지표
    private List<Object> samples;         // 샘플 입출력
    private List<Object> lineage;         // 계보(부모모델 등)
    private Map<String, Object> access;   // 접근방식(권한/토큰/구독레벨)
    private Map<String, Object> ioLimits; // 입출력 한도

    /* IPFS */
    @NotBlank private String cidRoot;      // ipfsCid
    @NotBlank private String checksumRoot; // 무결성 체크

    /* 온체인 관련 입력(프론트 전달) */
    @NotBlank private String developerWallet;      // base58 pubkey
    private String developerSignature;             // optional

    /** 온체인 tx에 들어갈 가격(라몰트 단위의 문자열, u64 범위) */
    @Pattern(regexp = "^[0-9]+$", message = "priceLamports must be a numeric string")
    @NotBlank
    private String priceLamports; // e.g. "10000000"

    /** 로열티 BPS (예: 250 = 2.5%) */
    @Min(0) @Max(10000)
    private Integer royaltyBps = 250;

    /** 부모모델: 단일 문자열 또는 리스트(JSON)는 lineage로 대체 가능하지만, 명시 필드도 허용 */
    private Object parentModel; // String or List
}
