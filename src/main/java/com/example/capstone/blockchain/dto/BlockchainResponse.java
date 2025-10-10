package com.example.capstone.blockchain.dto;

import lombok.Data;

@Data
public class BlockchainResponse {
    private String pda;          // 모델의 온체인 PDA
    private String txSignature;  // 트랜잭션 서명
    private String status;       // SUCCESS / FAIL / DUMMY
}
