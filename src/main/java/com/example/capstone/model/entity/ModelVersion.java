// src/main/java/com/example/capstone/model/entity/ModelVersion.java
package com.example.capstone.model.entity;

import com.example.capstone.user.entity.AppUser;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "model_versions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModelVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 🔗 모델 연결 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    @Column(name = "version_name", nullable = false, length = 50)
    private String versionName; // 1.0.0

    @Enumerated(EnumType.STRING)
    private ModelVersionStatus status;

    /** 🔗 모달리티 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modality_id", nullable = false)
    private Modality modality;

    /** 🔗 라이선스 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "license_id", nullable = false)
    private License license;

    /** 가격/통화 */
    @Builder.Default
    @Column(length = 10, nullable = false)
    private String currency = "USDC";

    @Column(name = "price_research")
    private BigDecimal priceResearch;

    @Column(name = "price_standard")
    private BigDecimal priceStandard;

    @Column(name = "price_enterprise")
    private BigDecimal priceEnterprise;

    /** 개요 및 부가 정보 */
    @Column(columnDefinition = "TEXT")
    private String overview;

    @Column(name = "release_notes", columnDefinition = "TEXT")
    private String releaseNotes;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "cid_root", length = 255)
    private String cidRoot;

    @Column(name = "checksum_root", length = 255)
    private String checksumRoot;

    @Column(name = "onchain_tx", length = 255)
    private String onchainTx;

    @Column(name = "storage_json", columnDefinition = "TEXT")
    private String storageJson;

    @Column(name = "metrics_json", columnDefinition = "TEXT")
    private String metricsJson;

    @Column(name = "samples_json", columnDefinition = "TEXT")
    private String samplesJson;

    @Column(name = "lineage_json", columnDefinition = "TEXT")
    private String lineageJson;

    // 추가
    @Column(columnDefinition = "TEXT")
    private String accessJson;

    // 추가
    @Column(columnDefinition = "TEXT")
    private String ioLimitsJson;

    /** 등록 정보 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uploader_id", nullable = false)
    private AppUser uploader;


    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;
}