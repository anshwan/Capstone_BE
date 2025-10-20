package com.example.capstone.model.dto;

import lombok.*;
import java.time.LocalDate;
import java.util.Map;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModelUploadRequest {

    private String name;
    private String uploader;
    private String versionName;
    private String modality;
    private List<String> license;
    private Map<String, Object> pricing;

    private String walletAddress;
    private LocalDate releaseDate;
    private String overview;
    private String releaseNotes;
    private String thumbnail;

    private Map<String, Object> metrics;
    private Map<String, Object> technicalSpecs;
    private Map<String, String> sample;

    private String cidRoot;
    private String encryptionKey;

    // ✅ lineage: 프론트에서 전송되는 계보 정보
    private Lineage lineage;

    // ✅ 선택적 서명
    private String developerSignature;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Lineage {
        private String parentModelId;   // "123"
        private String relationship;    // "fine-tuned-from"
    }
}
