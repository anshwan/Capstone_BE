// src/main/java/com/example/capstone/payment/dto/PaymentResponse.java
package com.example.capstone.payment.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponse {
    private boolean success;
    private Long receiptId;
    private String transactionHash;
    private String receiptPda;
    private String status;  // PENDING / VERIFIED / FAILED
}
