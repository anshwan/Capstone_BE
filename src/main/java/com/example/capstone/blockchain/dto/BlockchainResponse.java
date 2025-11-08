package com.example.capstone.blockchain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BlockchainResponse {

    @JsonProperty("pda")
    private String pda;

    @JsonProperty("transactionSignature") // ✅ 서버 필드와 매핑
    private String txSignature;           // ✅ 기존 코드 사용 가능

    private String status; // 서버에서 주면 매핑됨, 없어도 null 가능
}
