package com.example.capstone.model.entity;

import com.example.capstone.user.entity.AppUser;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "model_versions", uniqueConstraints = @UniqueConstraint(name="uq_model_version", columnNames={"model_id","version_name"}))
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModelVersion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false) @JoinColumn(name="model_id")
    private Model model;

    @Column(name="version_name", nullable=false, length=50)
    private String versionName;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false, length=16)
    @Builder.Default
    private ModelVersionStatus status = ModelVersionStatus.PUBLISHED;

    // 버전 기준 메타
    @ManyToOne(optional=false) @JoinColumn(name="modality_id")
    private Modality modality;

    @ManyToOne(optional=false) @JoinColumn(name="license_id")
    private LicenseDef license;

    @ManyToOne(optional=false) @JoinColumn(name="uploader_id")
    private AppUser uploader;

    // 설명/릴리스
    @Column(columnDefinition = "text")
    private String overview;

    @Column(name="release_notes", columnDefinition = "longtext")
    private String releaseNotes;

    private LocalDate releaseDate;

    // 저장/무결성/온체인
    @Column(name="cid_root", length=128) private String cidRoot;
    @Column(name="checksum_root", length=128) private String checksumRoot;
    @Column(name="onchain_tx", length=128) private String onchainTx;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name="storage_json", columnDefinition = "json")
    private JsonNode storageJson;

    // 여러 개 항목(JSON)
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name="metrics_json", columnDefinition = "json")
    private JsonNode metricsJson;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name="samples_json", columnDefinition = "json")
    private JsonNode samplesJson;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name="lineage_json", columnDefinition = "json")
    private JsonNode lineageJson;

    @Column(name="created_at", nullable=false, updatable=false, insertable=false,
            columnDefinition = "timestamp default current_timestamp")
    private Instant createdAt;

    @Column(name="updated_at", nullable=false, insertable=false,
            columnDefinition = "timestamp default current_timestamp on update current_timestamp")
    private Instant updatedAt;
}
