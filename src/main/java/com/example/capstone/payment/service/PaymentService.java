// src/main/java/com/example/capstone/payment/service/PaymentService.java
package com.example.capstone.payment.service;

import com.example.capstone.blockchain.service.BlockchainService;
import com.example.capstone.payment.dto.PaymentRequest;
import com.example.capstone.payment.dto.PaymentResponse;
import com.example.capstone.payment.entity.Receipt;
import com.example.capstone.payment.repository.ReceiptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final ReceiptRepository receiptRepository;
    private final BlockchainService blockchainService;

    /**
     * 🔹 결제 검증 + 영수증 생성
     * 프론트 요청 예시:
     * {
     *   "id": 1,
     *   "buyer": "openai_official",
     *   "plan": "standard",
     *   "price": 20,
     *   "onchainTx": "0x1a2b3c..."
     * }
     */
    @Transactional
    public PaymentResponse verifyAndCreateReceipt(PaymentRequest req) {
        String txHash = req.getOnchainTx();
        String buyerWallet = req.getBuyer();
        String plan = req.getPlan().toUpperCase(); // STANDARD / RESEARCH / ENTERPRISE
        Long amountLamports = req.getPrice();
        Long modelId = req.getId();

        // 1️⃣ 중복 트랜잭션 방지
        receiptRepository.findByOnchainTxHash(txHash)
                .ifPresent(r -> {
                    throw new IllegalArgumentException("이미 처리된 트랜잭션입니다. (txHash: " + txHash + ")");
                });

        // 2️⃣ 결제 정보 임시 저장 (상태: PENDING)
        Receipt receipt = Receipt.builder()
                .modelId(modelId)
                .buyerWallet(buyerWallet)
                .plan(plan)
                .amountLamports(amountLamports)
                .onchainTxHash(txHash)
                .status(Receipt.Status.PENDING)
                .build();
        receiptRepository.saveAndFlush(receipt); // flush로 즉시 DB 반영

        // 3️⃣ 온체인 검증 요청
        try {
            var result = blockchainService.verifyPurchase(
                    txHash,
                    buyerWallet,
                    amountLamports,
                    plan
            );

            if (result.isSuccess()) {
                // ✅ 검증 성공
                receipt.setStatus(Receipt.Status.VERIFIED);
                receipt.setReceiptPda(result.getSubscriptionReceiptPDA());
                System.out.println("✅ 결제 검증 성공: txHash=" + result.getTransactionHash());
            } else {
                // ❌ 블록체인 서버에서 실패 반환
                receipt.setStatus(Receipt.Status.FAILED);
                System.err.println("❌ 결제 검증 실패: txHash=" + txHash);
            }

            return PaymentResponse.builder()
                    .success(result.isSuccess())
                    .receiptId(receipt.getId())
                    .transactionHash(result.getTransactionHash())
                    .receiptPda(receipt.getReceiptPda())
                    .status(receipt.getStatus().name())
                    .build();

        } catch (Exception e) {
            // ⚠️ 통신 예외 발생 시 FAILED 처리
            receipt.setStatus(Receipt.Status.FAILED);
            System.err.println("⚠️ 블록체인 검증 예외 발생: " + e.getMessage());

            return PaymentResponse.builder()
                    .success(false)
                    .receiptId(receipt.getId())
                    .transactionHash(txHash)
                    .status(Receipt.Status.FAILED.name())
                    .build();
        }
    }

    /**
     * 🔹 영수증 상태 조회
     */
    @Transactional(readOnly = true)
    public PaymentResponse getReceiptStatus(Long id) {
        Receipt r = receiptRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("영수증을 찾을 수 없습니다. (id=" + id + ")"));

        return PaymentResponse.builder()
                .success(r.getStatus() == Receipt.Status.VERIFIED)
                .receiptId(r.getId())
                .transactionHash(r.getOnchainTxHash())
                .receiptPda(r.getReceiptPda())
                .status(r.getStatus().name())
                .build();
    }
}
