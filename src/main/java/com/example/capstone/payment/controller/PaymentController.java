// src/main/java/com/example/capstone/payment/controller/PaymentController.java
package com.example.capstone.payment.controller;

import com.example.capstone.payment.dto.PaymentRequest;
import com.example.capstone.payment.dto.PaymentResponse;
import com.example.capstone.payment.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 🔹 결제 검증 & 영수증 관리 컨트롤러
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
@Tag(name = "Payment API", description = "결제 검증 및 영수증 관리 API")
public class PaymentController {

    private final PaymentService paymentService;

    /**
     * ✅ 프론트엔드 → 결제 검증 요청
     */
    @Operation(
            summary = "결제 검증 요청",
            description = """
            프론트엔드에서 결제 완료 후, 
            블록체인 트랜잭션 해시(onchainTx)와 결제 메타데이터를 전달하면 
            서버가 온체인 검증을 수행하고 영수증을 생성합니다.
            
            🔸 요청 예시:
            {
              "id": "1",
              "name": "GPT-4 Turbo",
              "buyer": "openai_official",
              "versionName": "1.0.0",
              "pricing": {
                "standard": {
                  "price": 20,
                  "description": "표준",
                  "billingType": "monthly_subscription",
                  "monthlyTokenLimit": 1000000,
                  "rights": ["상업적", "API 액세스", "배포허용", "수정가능"]
                }
              },
              "onchainTx": "0x1a2b3c4d5e6f7890abcdef..."
            }
            """
    )
    @ApiResponse(
            responseCode = "200",
            description = "결제 검증 성공",
            content = @Content(schema = @Schema(implementation = PaymentResponse.class))
    )
    @ApiResponse(
            responseCode = "400",
            description = "잘못된 요청 또는 이미 처리된 트랜잭션",
            content = @Content
    )
    @PostMapping("/verify")
    public ResponseEntity<PaymentResponse> verifyPayment(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "결제 요청 데이터",
                    required = true,
                    content = @Content(schema = @Schema(implementation = PaymentRequest.class))
            )
            @RequestBody PaymentRequest req
    ) {
        PaymentResponse response = paymentService.verifyAndCreateReceipt(req);
        return ResponseEntity.ok(response);
    }

    /**
     * 🔎 영수증 상태 조회 (단일 ID)
     */
    @Operation(
            summary = "결제 영수증 상태 조회",
            description = "결제 후 생성된 영수증 ID를 이용해 상태(PENDING/VERIFIED/FAILED)를 조회합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "영수증 조회 성공",
            content = @Content(schema = @Schema(implementation = PaymentResponse.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "영수증을 찾을 수 없음",
            content = @Content
    )
    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getPaymentStatus(
            @Parameter(description = "조회할 영수증 ID", example = "1")
            @PathVariable Long id
    ) {
        PaymentResponse response = paymentService.getReceiptStatus(id);
        return ResponseEntity.ok(response);
    }

    /**
     * 🔹 특정 buyer(구매자)별 영수증 전체 조회
     */
    @Operation(
            summary = "구매자별 영수증 전체 조회",
            description = "특정 buyer(구매자)로 결제된 모든 영수증 내역을 조회합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "영수증 목록 조회 성공",
            content = @Content(schema = @Schema(implementation = PaymentResponse.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "해당 구매자의 영수증을 찾을 수 없음",
            content = @Content
    )
    @GetMapping("/buyer/{buyer}")
    public ResponseEntity<List<PaymentResponse>> getReceiptsByBuyer(
            @Parameter(description = "조회할 buyer (구매자)", example = "agentchaintest")
            @PathVariable String buyer
    ) {
        List<PaymentResponse> responses = paymentService.getReceiptsByBuyer(buyer);
        return ResponseEntity.ok(responses);
    }
}
