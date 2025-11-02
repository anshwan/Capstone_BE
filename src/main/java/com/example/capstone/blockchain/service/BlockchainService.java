package com.example.capstone.blockchain.service;

import com.example.capstone.blockchain.dto.BlockchainRequest;
import com.example.capstone.blockchain.dto.BlockchainResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BlockchainService {

    private final RestTemplate restTemplate = new RestTemplate();

    /** 🔹 기존 모델 등록 요청 */
    public BlockchainResponse registerOnChain(BlockchainRequest request) {
        try {
            String url = "https://35.216.87.44.sslip.io/api/transactions/register-model"; // 블록체인 백엔드 API
            return restTemplate.postForObject(url, request, BlockchainResponse.class);
        } catch (Exception e) {
            System.err.println("⚠️ Blockchain 연동 실패: " + e.getMessage());

            // ✅ 더미값 반환
            BlockchainResponse dummy = new BlockchainResponse();
            dummy.setPda("dummy_pda_" + UUID.randomUUID());
            dummy.setTxSignature("dummy_tx_" + UUID.randomUUID());
            dummy.setStatus("DUMMY_SUCCESS");
            return dummy;
        }
    }

    /** 🔹 수정됨: 결제 검증 요청 (transactionSignature만 전송) */
    public VerifyPurchaseResult verifyPurchase(String txHash) {
        try {
            String url = "https://35.216.87.44.sslip.io/api/transactions/process-signature-royalty";

            // ✅ 온체인 백엔드에서 요구하는 필드명으로 맞춤
            Map<String, Object> body = Map.of(
                    "transactionSignature", txHash
            );

            // 응답 예시: {"success":true,"transactionHash":"...","subscriptionReceiptPDA":"..."}
            Map<?, ?> response = restTemplate.postForObject(url, body, Map.class);

            boolean success = Boolean.TRUE.equals(response.get("success"));
            String transactionHash = (String) response.get("transactionHash");
            String receiptPda = (String) response.get("subscriptionReceiptPDA");

            System.out.println("🟢 요청 바디: " + body);
            System.out.println("🟢 요청 URL: " + url);
            System.out.println("🟢 응답 원본: " + response);

            return new VerifyPurchaseResult(success, transactionHash, receiptPda);

        } catch (Exception e) {
            e.printStackTrace(); // 전체 스택트레이스 출력
            System.err.println("⚠️ 결제 검증 실패: " + e);
            return new VerifyPurchaseResult(false, "dummy_tx_" + UUID.randomUUID(), null);
        }
    }

    /** 🔹 내부 응답 객체 (PaymentService와 연동용) */
    @lombok.Value
    public static class VerifyPurchaseResult {
        boolean success;
        String transactionHash;
        String receiptPda; // ✅ 수정됨 — 기존 subscriptionReceiptPDA → receiptPda
    }
}
