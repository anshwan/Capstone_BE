// src/main/java/com/example/capstone/payment/dto/PaymentRequest.java
package com.example.capstone.payment.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequest {
    private Long id;             // 모델 ID
    private String name;         // 모델 이름 (선택)
    private String buyer;        // 구매자
    private String versionName;  // 모델 버전 (선택)
    private String plan;         // 선택된 플랜 (research / standard / enterprise)
    private Long price;          // 결제 금액
    private String onchainTx;    // 트랜잭션 해시
}
