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

    private String parentModelId;
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

    // 선택적으로 서명값 포함 가능
    private String developerSignature;
}
