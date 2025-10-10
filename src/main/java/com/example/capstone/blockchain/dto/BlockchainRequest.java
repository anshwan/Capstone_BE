package com.example.capstone.blockchain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BlockchainRequest {
    private String developerWallet;
    private String developerSignature;
    private String modelName;
    private String ipfsCid;
    private String priceLamports; // string(u64)
    private int royaltyBps;
    private String parentModelPda; // 선택적
}
