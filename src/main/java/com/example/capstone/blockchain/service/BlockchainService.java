package com.example.capstone.blockchain.service;

import com.example.capstone.blockchain.dto.BlockchainResponse;
import com.example.capstone.model.dto.ModelUploadRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BlockchainService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 🔹 모델 등록 요청 (프론트 → 백엔드 → 온체인 서버)
     * 백엔드에서 받은 ModelUploadRequest 전체를 그대로 블록체인 서버로 전달
     */
    public BlockchainResponse registerOnChain(ModelUploadRequest modelRequest) {
        // ✅ Postman에서 실제로 확인된 라우트로 교체 필요
        String url = "https://35.216.87.44.sslip.io/api/transactions/register-model";

        try {
            // JSON 직렬화 (요청 바디 확인용)
            String jsonBody = objectMapper.writeValueAsString(modelRequest);

            System.out.println("🟢 [registerOnChain] 요청 URL: " + url);
            System.out.println("🟢 [registerOnChain] 요청 바디(JSON): " + jsonBody);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);

            ResponseEntity<BlockchainResponse> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    entity,
                    BlockchainResponse.class
            );

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                BlockchainResponse body = response.getBody();
                System.out.println("✅ [registerOnChain] 온체인 등록 성공");
                System.out.println("   ├─ status: " + body.getStatus());
                System.out.println("   ├─ pda: " + body.getPda());
                System.out.println("   └─ txSignature: " + body.getTxSignature());
                return body;
            } else {
                System.err.println("⚠️ [registerOnChain] 온체인 서버 응답 비정상: " + response.getStatusCode());
            }

        } catch (Exception e) {
            System.err.println("❌ [registerOnChain] 블록체인 연동 실패: " + e.getMessage());
            e.printStackTrace();
        }

        // ✅ 더미 응답 (온체인 서버 연결 실패 시)
        BlockchainResponse dummy = new BlockchainResponse();
        dummy.setPda("dummy_pda_" + UUID.randomUUID());
        dummy.setTxSignature("dummy_tx_" + UUID.randomUUID());
        dummy.setStatus("DUMMY_SUCCESS");
        System.out.println("⚠️ [registerOnChain] 더미 응답 반환: " + dummy);

        return dummy;
    }

    /**
     * 🔹 결제 검증 요청 (transactionSignature만 전송)
     */
    public VerifyPurchaseResult verifyPurchase(String txHash) {
        try {
            String url = "https://35.216.87.44.sslip.io/api/transactions/process-signature-royalty";

            Map<String, Object> body = Map.of("transactionSignature", txHash);

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
