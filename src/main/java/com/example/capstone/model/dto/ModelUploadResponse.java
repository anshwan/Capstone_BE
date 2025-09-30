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
    private String onchainTx;           // 트랜잭션 서명
    private String onchainModelId;      // 온체인 모델 ID (null 가능)
    private boolean onchainSucceeded;   // 온체인 성공 여부

    /* 저장소 */
    private String ipfsCid;             // IPFS CID
}
