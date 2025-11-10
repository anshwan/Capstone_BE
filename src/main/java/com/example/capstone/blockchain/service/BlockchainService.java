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
    private final ModelRepository modelRepository;

    /** ✅ LocalDate → yyyy-MM-dd 직렬화 지원 */
    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    /**
     * ✅ 모델 등록 → 온체인 서버 호출
     */
    public BlockchainResponse registerOnChain(ModelUploadRequest req) {

        String url = "https://35.216.87.44.sslip.io/api/transactions/register-model";

        try {
            Map<String, Object> body = new LinkedHashMap<>();

            // ✅ 기본 필드
            body.put("name", req.getName());
            body.put("uploader", req.getUploader());
            body.put("versionName", req.getVersionName());
            body.put("modality", req.getModality());
            body.put("license", req.getLicense());
            body.put("walletAddress", req.getWalletAddress());
            body.put("releaseDate", req.getReleaseDate().toString());
            body.put("overview", req.getOverview());
            body.put("releaseNotes", req.getReleaseNotes());
            body.put("thumbnail", req.getThumbnail());
            body.put("cidRoot", req.getCidRoot());
            body.put("encryptionKey", req.getEncryptionKey());
            body.put("pricing", req.getPricing());
            body.put("metrics", req.getMetrics());
            body.put("technicalSpecs", req.getTechnicalSpecs());

            // ✅ sample → 온체인 포맷에 맞게 변환
            Map<String, Object> sample = new LinkedHashMap<>();
            switch (req.getModality().trim().toLowerCase()) {
                case "llm" -> {
                    sample.put("input", req.getSample().get("sample_prompt"));
                    sample.put("output", req.getSample().get("sample_output"));
                }
                case "image", "image_generation" -> {
                    sample.put("input", req.getSample().get("sample_prompt"));
                    sample.put("output", req.getSample().get("sample_output_image"));
                }
                case "audio" -> {
                    sample.put("input", req.getSample().get("sample_input_audio"));
                    sample.put("output", req.getSample().get("sample_output"));
                }
                case "multimodal" -> {
                    sample.put("input", req.getSample().get("sample_input_image"));
                    sample.put("output", req.getSample().get("sample_output"));
                }
            }
            body.put("sample", sample);

            // ✅ lineage → parentModelPDA 전달
            if (req.getLineage() != null && req.getLineage().getParentModelId() != null) {
                Long parentId = Long.valueOf(req.getLineage().getParentModelId());
                Model parentModel = modelRepository.findById(parentId).orElse(null);

                if (parentModel != null && parentModel.getPda() != null) {
                    body.put("parentModelPDA", parentModel.getPda());
                    body.put("relationship", req.getLineage().getRelationship());
                }
            }

            String jsonBody = objectMapper.writeValueAsString(body);
            System.out.println("🟢 [registerOnChain] 전송 JSON → " + jsonBody);

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
                System.out.println("✅ [registerOnChain] 온체인 성공: " + response.getBody());
                return response.getBody();
            }

        } catch (Exception e) {
            System.err.println("❌ [registerOnChain] 실패: " + e.getMessage());
        }

        // ✅ 장애 대비 Dummy 응답 (개발 환경 안전 모드)
        BlockchainResponse dummy = new BlockchainResponse();
        dummy.setTxSignature("dummy_tx_" + UUID.randomUUID());

        BlockchainResponse.DataField data = new BlockchainResponse.DataField();
        data.setPda("dummy_pda_" + UUID.randomUUID());
        dummy.setData(data);

        return dummy;
    }

    /**
     * ✅ 결제 검증 (Tx + modelPDA 기반 로열티 분배)
     */
    public VerifyPurchaseResult verifyPurchase(String txHash, String modelPda) {
        try {
            String url = "https://35.216.87.44.sslip.io/api/signature-royalty/process-signature-royalty";

            Map<String, Object> body = Map.of(
                    "transactionSignature", txHash,
                    "modelPDA", modelPda
            );

            Map<?, ?> response = restTemplate.postForObject(url, body, Map.class);

            // ✅ 여기 추가 → 온체인 백엔드 응답 전체를 그대로 로그 출력
            try {
                String rawJson = objectMapper.writeValueAsString(response);
                System.out.println("🟡 [verifyPurchase] 온체인 응답 RAW → " + rawJson);
            } catch (Exception e) {
                System.err.println("⚠️ RAW JSON 로그 변환 실패: " + e.getMessage());
            }

            boolean success = Boolean.TRUE.equals(response.get("success"));
            String message = (String) response.get("message");

            Map<?, ?> data = (Map<?, ?>) response.get("data");

            String transactionHash = data != null ? String.valueOf(data.get("transactionHash")) : null;
            String reason = data != null ? String.valueOf(data.get("reason")) : null;
            String receiptPda = data != null ? String.valueOf(data.get("subscriptionReceiptPDA")) : null;

            return new VerifyPurchaseResult(success, transactionHash, receiptPda, reason, message);

        } catch (Exception e) {
            System.err.println("⚠️ [verifyPurchase] RPC 또는 온체인 서버 오류: " + e.getMessage());
            return new VerifyPurchaseResult(false, txHash, null, "NETWORK_ERROR", e.getMessage());
        }
    }

    /** ✅ 내부 응답 구조 */
    @lombok.Value
    public static class VerifyPurchaseResult {
        boolean success;
        String transactionHash;
        String receiptPda;
        String reason;   // ✅ 실패 원인
        String message;  // ✅ 에러/설명 메시지
    }
}
