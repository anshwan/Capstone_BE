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

    /** 🔹 모델 등록 요청 (온체인 연동 확인용 로그 포함) */
    public BlockchainResponse registerOnChain(BlockchainRequest request) {
        String url = "https://35.216.87.44.sslip.io/api/transactions/register-model"; // 블록체인 백엔드 API
        try {
            System.out.println("🟢 [registerOnChain] 요청 URL: " + url);
            System.out.println("🟢 [registerOnChain] 요청 바디: " + request);

            BlockchainResponse response = restTemplate.postForObject(url, request, BlockchainResponse.class);

            if (response != null) {
                System.out.println("✅ [registerOnChain] 온체인 응답 수신 성공:");
                System.out.println("   ├─ status: " + response.getStatus());
                System.out.println("   ├─ pda: " + response.getPda());
                System.out.println("   └─ txSignature: " + response.getTxSignature());
            } else {
                System.err.println("⚠️ [registerOnChain] 응답이 null 입니다. (블록체인 서버 응답 없음)");
            }

            return response;

        } catch (Exception e) {
            System.err.println("❌ [registerOnChain] 블록체인 연동 실패: " + e.getMessage());
            e.printStackTrace();

            // ✅ 더미값 반환 (테스트용)
            BlockchainResponse dummy = new BlockchainResponse();
            dummy.setPda("dummy_pda_" + UUID.randomUUID());
            dummy.setTxSignature("dummy_tx_" + UUID.randomUUID());
            dummy.setStatus("DUMMY_SUCCESS");

            System.out.println("⚠️ [registerOnChain] 더미 응답 반환: " + dummy);
            return dummy;
        }
    }

    /** 🔹 결제 검증 요청 (transactionSignature만 전송) */
    public VerifyPurchaseResult verifyPurchase(String txHash) {
        try {
            String url = "https://35.216.87.44.sslip.io/api/transactions/process-signature-royalty";

            Map<String, Object> body = Map.of(
                    "transactionSignature", txHash
            );

            System.out.println("🟢 [verifyPurchase] 요청 URL: " + url);
            System.out.println("🟢 [verifyPurchase] 요청 바디: " + body);

            Map<?, ?> response = restTemplate.postForObject(url, body, Map.class);

            System.out.println("🟢 [verifyPurchase] 응답 원본: " + response);

            boolean success = Boolean.TRUE.equals(response.get("success"));
            String transactionHash = (String) response.get("transactionHash");
            String receiptPda = (String) response.get("subscriptionReceiptPDA");

            if (success) {
                System.out.println("✅ [verifyPurchase] 결제 검증 성공: " + transactionHash);
            } else {
                System.err.println("❌ [verifyPurchase] 결제 검증 실패: " + response);
            }

            return new VerifyPurchaseResult(success, transactionHash, receiptPda);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("⚠️ [verifyPurchase] 예외 발생: " + e);
            return new VerifyPurchaseResult(false, "dummy_tx_" + UUID.randomUUID(), null);
        }
    }

    /** 🔹 내부 응답 객체 (PaymentService와 연동용) */
    @lombok.Value
    public static class VerifyPurchaseResult {
        boolean success;
        String transactionHash;
        String receiptPda;
    }
}
