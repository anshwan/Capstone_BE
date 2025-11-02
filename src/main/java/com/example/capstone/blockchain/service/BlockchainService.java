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

    /** 🔹 새로 추가: 결제 검증 요청 */
    public VerifyPurchaseResult verifyPurchase(String txHash,
                                               String buyerWallet,
                                               long amountLamports,
                                               String plan) {
        try {
            // 블록체인 백엔드 결제 검증 API
            String url = "https://35.216.87.44.sslip.io/api/transactions/process-signature-royalty";

            Map<String, Object> body = Map.of(
                    "txHash", txHash,
                    "buyerWallet", buyerWallet,
                    "amountLamports", amountLamports,
                    "plan", plan
            );

            // 응답은 예: {"success":true,"transactionHash":"...","subscriptionReceiptPDA":"..."}
            Map<?, ?> response = restTemplate.postForObject(url, body, Map.class);

            boolean success = Boolean.TRUE.equals(response.get("success"));
            String transactionHash = (String) response.get("transactionHash");
            String receiptPda = (String) response.get("subscriptionReceiptPDA");

            return new VerifyPurchaseResult(success, transactionHash, receiptPda);

        } catch (Exception e) {
            System.err.println("⚠️ 결제 검증 실패: " + e.getMessage());
            return new VerifyPurchaseResult(false, "dummy_tx_" + UUID.randomUUID(), null);
        }
    }

    /** 🔹 내부 응답 객체 (PaymentService와 연동용) */
    @lombok.Value
    public static class VerifyPurchaseResult {
        boolean success;
        String transactionHash;
        String subscriptionReceiptPDA;
    }
}
