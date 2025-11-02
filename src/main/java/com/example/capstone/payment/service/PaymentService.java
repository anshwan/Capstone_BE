package com.example.capstone.payment.service;

import com.example.capstone.blockchain.service.BlockchainService;
import com.example.capstone.payment.dto.PaymentRequest;
import com.example.capstone.payment.dto.PaymentResponse;
import com.example.capstone.payment.entity.Receipt;
import com.example.capstone.payment.repository.ReceiptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final ReceiptRepository receiptRepository;
    private final BlockchainService blockchainService;

    /** 🔹 결제 검증 + 영수증 생성 */
    @Transactional
    public PaymentResponse verifyAndCreateReceipt(PaymentRequest req) {
        String txHash = req.getOnchainTx();
        String buyer = req.getBuyer();
        String plan = req.getPlan() != null ? req.getPlan().toUpperCase() : "STANDARD";
        Long modelId = req.getId();

        // ✅ 선택된 플랜의 결제 금액 추출
        Long amountLamports = null;
        try {
            switch (plan) {
                case "RESEARCH" -> {
                    if (req.getPricing().getResearch() != null)
                        amountLamports = req.getPricing().getResearch().getPrice();
                }
                case "ENTERPRISE" -> {
                    if (req.getPricing().getEnterprise() != null)
                        amountLamports = req.getPricing().getEnterprise().getPrice();
                }
                default -> {
                    if (req.getPricing().getStandard() != null)
                        amountLamports = req.getPricing().getStandard().getPrice();
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("요금제 정보가 올바르지 않습니다: " + e.getMessage());
        }

        if (amountLamports == null) {
            throw new IllegalArgumentException("선택된 플랜(" + plan + ")의 결제 금액을 찾을 수 없습니다.");
        }

        // 1️⃣ 중복 트랜잭션 방지
        receiptRepository.findByOnchainTxHash(txHash)
                .ifPresent(r -> {
                    throw new IllegalArgumentException("이미 처리된 트랜잭션입니다. (txHash: " + txHash + ")");
                });

        // 2️⃣ 결제 정보 임시 저장 (상태: PENDING)
        Receipt receipt = Receipt.builder()
                .modelId(modelId)
                .buyer(buyer)
                .plan(plan)
                .amountLamports(amountLamports)
                .onchainTxHash(txHash)
                .status(Receipt.Status.PENDING)
                .build();
        receiptRepository.saveAndFlush(receipt);

        // 3️⃣ 온체인 검증 요청 (transactionSignature만 전송)
        try {
            var result = blockchainService.verifyPurchase(txHash);

            if (result.isSuccess()) {
                receipt.setStatus(Receipt.Status.VERIFIED);
                receipt.setReceiptPda(result.getReceiptPda()); // ✅ 이름 수정됨
                receiptRepository.save(receipt); // ✅ 상태 반영
                System.out.println("✅ 결제 검증 성공: txHash=" + result.getTransactionHash());
            } else {
                receipt.setStatus(Receipt.Status.FAILED);
                receiptRepository.save(receipt); // ✅ 상태 반영
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
            receipt.setStatus(Receipt.Status.FAILED);
            receiptRepository.save(receipt); // ✅ 예외 시에도 저장
            System.err.println("⚠️ 블록체인 검증 예외 발생: " + e.getMessage());

            return PaymentResponse.builder()
                    .success(false)
                    .receiptId(receipt.getId())
                    .transactionHash(txHash)
                    .status(Receipt.Status.FAILED.name())
                    .build();
        }
    }

    /** 🔹 영수증 상태 조회 */
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

    /** 🔹 구매자별 영수증 전체 조회 */
    @Transactional(readOnly = true)
    public List<PaymentResponse> getReceiptsByBuyer(String buyer) {
        List<Receipt> receipts = receiptRepository.findAllByBuyer(buyer);

        if (receipts.isEmpty()) {
            throw new IllegalArgumentException("해당 구매자의 영수증이 존재하지 않습니다. (buyer=" + buyer + ")");
        }

        return receipts.stream()
                .map(r -> PaymentResponse.builder()
                        .success(r.getStatus() == Receipt.Status.VERIFIED)
                        .receiptId(r.getId())
                        .transactionHash(r.getOnchainTxHash())
                        .receiptPda(r.getReceiptPda())
                        .status(r.getStatus().name())
                        .build())
                .toList();
    }
}
