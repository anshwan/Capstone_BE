// src/main/java/com/example/capstone/payment/controller/PaymentController.java
package com.example.capstone.payment.controller;

import com.example.capstone.payment.dto.PaymentRequest;
import com.example.capstone.payment.dto.PaymentResponse;
import com.example.capstone.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 🔹 결제 검증 & 영수증 관리 컨트롤러
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    /**
     * ✅ 프론트엔드 → 결제 검증 요청
     * @param req PaymentRequest
     * @return PaymentResponse (success, receiptId, txHash, status 등)
     */
    @PostMapping("/verify")
    public ResponseEntity<PaymentResponse> verifyPayment(@RequestBody PaymentRequest req) {
        PaymentResponse response = paymentService.verifyAndCreateReceipt(req);
        return ResponseEntity.ok(response);
    }

    /**
     * 🔎 영수증 상태 조회 API
     * GET /api/payments/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getPaymentStatus(@PathVariable Long id) {
        PaymentResponse response = paymentService.getReceiptStatus(id);
        return ResponseEntity.ok(response);
    }
}
