package com.example.capstone.blockchain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BlockchainResponse {

    @JsonProperty("transactionHash")
    private String txSignature; // ✅ 기존 onchainTx 에 쓰던 값

    @JsonProperty("data")
    private DataField data; // ✅ 내부 data 객체 매핑

    @Data
    public static class DataField {
        @JsonProperty("modelAccountPDA")
        private String pda; // ✅ 모델 PDA
    }
}
