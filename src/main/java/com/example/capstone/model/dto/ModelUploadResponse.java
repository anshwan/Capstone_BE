// src/main/java/com/example/capstone/model/dto/ModelUploadResponse.java
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

    /* 온체인 결과 */
    private String onchainTx;           // tx signature (실패 시 더미)
    private String onchainModelId;      // 온체인에서 발급된 모델 식별자(옵션, 실패시 null)
    private boolean onchainSucceeded;   // true/false

    /* 저장소 */
    private String ipfsCid;             // cidRoot
}
