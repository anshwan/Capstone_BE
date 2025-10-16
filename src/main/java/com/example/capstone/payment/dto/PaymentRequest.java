// src/main/java/com/example/capstone/payment/dto/PaymentRequest.java
package com.example.capstone.payment.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequest {
    private Long id;             // 모델 ID
    private String name;         // 모델 이름
    private String buyer;        // 구매자
    private String versionName;  // 모델 버전
    private String plan;         // ✅ 선택된 요금제 이름 (research / standard / enterprise)
    private Pricing pricing;     // ✅ 요금제 전체 객체
    private String onchainTx;    // 트랜잭션 해시

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Pricing {
        private Plan research;
        private Plan standard;
        private Plan enterprise;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Plan {
        private Long price;
        private String description;
        private String billingType;
        private Long monthlyTokenLimit;
        private Long monthlyGenerationLimit;
        private Long monthlyRequestLimit;
        private List<String> rights;
    }
}
