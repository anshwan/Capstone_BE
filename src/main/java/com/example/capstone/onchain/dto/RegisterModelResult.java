// src/main/java/com/example/capstone/onchain/dto/RegisterModelResult.java
package com.example.capstone.onchain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterModelResult {
    private boolean success;
    private String txSignature;   // 온체인 트랜잭션 시그니처
    private String onchainModelId; // 온체인에서 관리하는 모델 ID(있다면)
    private String message;       // 실패/성공 메시지
}
