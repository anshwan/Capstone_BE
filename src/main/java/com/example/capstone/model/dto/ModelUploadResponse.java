package com.example.capstone.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ModelUploadResponse {
    private Long modelId;
    private Long versionId;
    private String modelName;
    private String versionName;
    private String onchainTx;
    private String ipfsCid;
}
