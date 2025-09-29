// src/main/java/com/example/capstone/onchain/dto/RegisterModelRequest.java
package com.example.capstone.onchain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterModelRequest {
    // "register_model" payload와 동일한 키/의미
    private String developerWallet;    // base58
    private String developerSignature; // optional
    private String modelId;            // string(백엔드에서 미리 생성해도 되고 DB id 사용 전 임시 GUID 등)
    private String modelName;
    private String ipfsCid;
    private String priceLamports;      // u64 as string
    private Integer royaltyBps;        // e.g., 250
    private Object parentModel;        // String or List
}
