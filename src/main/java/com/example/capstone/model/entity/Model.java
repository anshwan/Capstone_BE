package com.example.capstone.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "models")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String name;               // 모델명

    @Column(nullable = false, length = 200)
    private String uploader;           // 업로더/회사명

    @Column(name="version_name", nullable = false, length = 100)
    private String versionName;        // 버전명

    @Enumerated(EnumType.STRING)
    private Modality modality;         // LLM, IMAGE_GENERATION, AUDIO, MULTIMODAL

    @Column(columnDefinition = "json")
    private String license;            // ["research","commercial"]

    private LocalDate releaseDate;

    @Column(columnDefinition = "TEXT")
    private String overview;

    private String compliance;
    private String thumbnail;
    private String cidRoot;
    private String checksumRoot;
    private String onchainTx;
    private String modelPda;
    private String txSignature;

    // 연관관계
    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PricingPlan> pricingPlans;

    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReleaseNote> releaseNotes;

    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lineage> lineage;

    /** ✅ 새로 추가 */
    @Column(name = "pda", length = 200)
    private String pda;   // 블록체인에서 반환된 PDA

    @Column(name = "encryption_key", length = 512)
    private String encryptionKey; // ✅ IPFS 암호화 키 (평문 저장, 내부 복호화용)

}
