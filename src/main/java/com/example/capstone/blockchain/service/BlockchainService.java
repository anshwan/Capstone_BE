package com.example.capstone.blockchain.service;

import com.example.capstone.blockchain.dto.BlockchainResponse;
import com.example.capstone.model.dto.ModelUploadRequest;
import com.example.capstone.model.entity.Model;
import com.example.capstone.model.repository.ModelRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BlockchainService {

    private final RestTemplate restTemplate = new RestTemplate();

    private final ModelRepository modelRepository; // ✅ DB 조회 위해 추가

    /** ✅ LocalDate 직렬화 지원 */
    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    /**
     * ✅ 모델 등록 요청 (프론트 → 백엔드 → 온체인 서버)
     */
    public BlockchainResponse registerOnChain(ModelUploadRequest req) {
        String url = "https://35.216.87.44.sslip.io/api/transactions/register-model";

        try {
            Map<String, Object> body = new LinkedHashMap<>();

            // ✅ 공통 필드 매핑
            body.put("name", req.getName());
            body.put("uploader", req.getUploader());
            body.put("versionName", req.getVersionName());
            body.put("modality", req.getModality());
            body.put("license", req.getLicense());
            body.put("walletAddress", req.getWalletAddress());
            body.put("releaseDate", req.getReleaseDate());
            body.put("overview", req.getOverview());
            body.put("releaseNotes", req.getReleaseNotes());
            body.put("thumbnail", req.getThumbnail());
            body.put("cidRoot", req.getCidRoot());
            body.put("encryptionKey", req.getEncryptionKey());
            body.put("pricing", req.getPricing());
            body.put("metrics", req.getMetrics());
            body.put("technicalSpecs", req.getTechnicalSpecs());
            body.put("sample", req.getSample());

            // ✅ lineage → parentModelPDA 변환 반영
            if (req.getLineage() != null && req.getLineage().getParentModelId() != null) {
                Long parentId = Long.valueOf(req.getLineage().getParentModelId());
                Model parentModel = modelRepository.findById(parentId).orElse(null);

                if (parentModel != null && parentModel.getPda() != null) {
                    body.put("parentModelPDA", parentModel.getPda());
                    body.put("relationship", req.getLineage().getRelationship());
                }
            }

            String jsonBody = objectMapper.writeValueAsString(body);
            System.out.println("🟢 [registerOnChain] 전송 JSON: " + jsonBody);

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
                return response.getBody();
            }

        } catch (Exception e) {
            System.err.println("❌ [registerOnChain] 실패: " + e.getMessage());
        }

        // ✅ 더미 응답 (온체인 서버 장애 대비)
        BlockchainResponse dummy = new BlockchainResponse();
        dummy.setPda("dummy_pda_" + UUID.randomUUID());
        dummy.setTxSignature("dummy_tx_" + UUID.randomUUID());
        dummy.setStatus("DUMMY_SUCCESS");
        return dummy;
    }

    /**
     * 🔹 결제 검증 요청
     */
    public VerifyPurchaseResult verifyPurchase(String txHash) {
        try {
            String url = "https://35.216.87.44.sslip.io/api/signature-royalty/process-signature-royalty";

            Map<String, Object> body = Map.of("transactionSignature", txHash);

            Map<?, ?> response = restTemplate.postForObject(url, body, Map.class);

            boolean success = Boolean.TRUE.equals(response.get("success"));
            String transactionHash = (String) response.get("transactionHash");
            String receiptPda = (String) response.get("subscriptionReceiptPDA");

            return new VerifyPurchaseResult(success, transactionHash, receiptPda);

        } catch (Exception e) {
            return new VerifyPurchaseResult(false, "dummy_tx_" + UUID.randomUUID(), null);
        }
    }

    /** 🔹 내부 응답 객체 */
    @lombok.Value
    public static class VerifyPurchaseResult {
        boolean success;
        String transactionHash;
        String receiptPda;
    }
}
