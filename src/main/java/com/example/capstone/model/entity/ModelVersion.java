package com.example.capstone.model.entity;

import com.example.capstone.user.entity.AppUser;
import jakarta.persistence.*;
import lombok.*;

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

    /** 버전명 */
    @Column(name = "version_name", nullable = false, length = 50)
    private String versionName;

    /** 상태 (예: ACTIVE, DEPRECATED) */
    @Enumerated(EnumType.STRING)
    private ModelVersionStatus status;

    /** 모달리티 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modality_id", nullable = false)
    private Modality modality;

    /** 출시일 */
    @Column(name = "release_date")
    private LocalDate releaseDate;

    /** 개요 */
    @Column(columnDefinition = "TEXT")
    private String overview;

    /** JSON 컬럼들 */
    @Column(name = "license_json", columnDefinition = "TEXT")
    private String licenseJson;

    @Column(name = "pricing_json", columnDefinition = "TEXT")
    private String pricingJson;

    @Column(name = "metrics_json", columnDefinition = "TEXT")
    private String metricsJson;

    @Column(name = "samples_json", columnDefinition = "TEXT")
    private String samplesJson;

    @Column(name = "lineage_json", columnDefinition = "TEXT")
    private String lineageJson;

    @Column(name = "technical_specs_json", columnDefinition = "TEXT")
    private String technicalSpecsJson;

    @Column(name = "release_notes_json", columnDefinition = "TEXT")
    private String releaseNotesJson;

    /** 온체인 관련 */
    @Column(name = "cid_root", length = 255)
    private String cidRoot;

    @Column(name = "checksum_root", length = 255)
    private String checksumRoot;

    @Column(name = "onchain_tx", length = 255)
    private String onchainTx;

    /** 업로더 (FK) */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uploader_id")
    private AppUser uploader;
}
