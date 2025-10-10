// ModelUploadRequest.java
package com.example.capstone.model.dto;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ModelUploadRequest {

    @NotBlank
    private String developerWallet;
    private String developerSignature;

    @NotBlank
    private String modelName;
    private String versionName;
    private String modality;
    private List<String> license;

    private String overview;
    private String compliance;
    private String sampleOutput;
    private List<String> releaseNotes;
    private LocalDate releaseDate;

    private JsonNode metrics;
    private JsonNode pricing;

    private String cidRoot;
    private String checksumRoot;

    // 부모 모델 이름 (선택적)
    private String parentModelName;
}
