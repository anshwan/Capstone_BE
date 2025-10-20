package com.example.capstone.model.service;

import com.example.capstone.blockchain.client.BlockchainClient;
import com.example.capstone.blockchain.dto.BlockchainRequest;
import com.example.capstone.blockchain.dto.BlockchainResponse;
import com.example.capstone.blockchain.service.BlockchainService;
import com.example.capstone.model.dto.ModelUploadRequest;
import com.example.capstone.model.entity.*;
import com.example.capstone.model.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ModelService {

    private final ModelRepository modelRepository;
    private final LlmSpecsRepository llmSpecsRepository;
    private final ImageSpecsRepository imageSpecsRepository;
    private final AudioSpecsRepository audioSpecsRepository;
    private final MultimodalSpecsRepository multimodalSpecsRepository;
    private final LineageRepository lineageRepository;
    private final ReleaseNoteRepository releaseNoteRepository;
    private final PricingPlanRepository pricingPlanRepository;

    private final BlockchainService blockchainService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    /* =============================================================
       ✅ 모델 업로드
       ============================================================= */
    @Transactional
    public Long uploadModel(ModelUploadRequest req) {

        // (1) 온체인 등록
        BlockchainResponse chainRes = blockchainService.registerOnChain(
                BlockchainRequest.builder()
                        .developerWallet(req.getWalletAddress())
                        .developerSignature(req.getDeveloperSignature())
                        .modelName(req.getName())
                        .ipfsCid(req.getCidRoot())
                        .priceLamports("100000000")
                        .royaltyBps(250)
                        .build()
        );

        // (2) license 직렬화
        String licenseJson = "[]";
        try {
            if (req.getLicense() != null && !req.getLicense().isEmpty()) {
                licenseJson = objectMapper.writeValueAsString(req.getLicense());
            }
        } catch (Exception ignored) {}

        // (3) 모델 기본 정보 저장
        Model model = Model.builder()
                .name(req.getName())
                .uploader(req.getUploader() != null ? req.getUploader() : req.getWalletAddress())
                .versionName(req.getVersionName())
                .modality(Modality.valueOf(req.getModality().toUpperCase()))
                .license(licenseJson)
                .overview(req.getOverview())
                .releaseDate(req.getReleaseDate())
                .thumbnail(req.getThumbnail())
                .cidRoot(req.getCidRoot())
                .encryptionKey(req.getEncryptionKey())
                .pda(chainRes.getPda())
                .onchainTx(chainRes.getTxSignature())
                .build();

        modelRepository.saveAndFlush(model);

        /* -------------------------------------------------------------
           ✅ lineage 저장
           ------------------------------------------------------------- */
        if (req.getLineage() != null && req.getLineage().getParentModelId() != null) {
            String parentModelId = req.getLineage().getParentModelId();
            String relationship = req.getLineage().getRelationship();

            String fromModel = modelRepository.findById(Long.valueOf(parentModelId))
                    .map(Model::getName).orElse(null);

            Relationship relationEnum;
            try {
                relationEnum = Relationship.valueOf(relationship.trim().toLowerCase().replace("-", "_"));
            } catch (Exception e) {
                relationEnum = Relationship.iteration;
            }

            Integer currentMax = lineageRepository.findMaxStepByModelId(model.getId());
            int nextStep = (currentMax == null ? 0 : currentMax) + 1;

            Lineage lineage = Lineage.builder()
                    .model(model)
                    .step(nextStep)
                    .fromModel(fromModel)
                    .toModel(model.getName())
                    .relationship(relationEnum)
                    .build();
            lineageRepository.save(lineage);
        }

        /* -------------------------------------------------------------
           ✅ release_notes 저장
           ------------------------------------------------------------- */
        if (req.getReleaseNotes() != null) {
            ReleaseNote note = ReleaseNote.builder()
                    .model(model)
                    .note(req.getReleaseNotes())
                    .build();
            releaseNoteRepository.save(note);
        }

        /* -------------------------------------------------------------
           ✅ pricing_plans 저장
           ------------------------------------------------------------- */
        if (req.getPricing() != null) {
            for (Map.Entry<String, Object> entry : req.getPricing().entrySet()) {
                Map<String, Object> plan = (Map<String, Object>) entry.getValue();
                PricingPlan entity = PricingPlan.builder()
                        .model(model)
                        .planType(PlanType.valueOf(entry.getKey().toUpperCase()))
                        .price(getDouble(plan.get("price")))
                        .description(getString(plan.get("description")))
                        .billingType(BillingType.valueOf(getString(plan.get("billingType")).toUpperCase()))
                        .rights(writeJson(plan.get("rights")))
                        .monthlyTokenLimit(getInt(plan.get("monthlyTokenLimit")))
                        .monthlyGenerationLimit(getInt(plan.get("monthlyGenerationLimit")))
                        .monthlyRequestLimit(getInt(plan.get("monthlyRequestLimit")))
                        .build();
                pricingPlanRepository.save(entity);
            }
        }

        /* -------------------------------------------------------------
           ✅ modality별 specs 저장
           ------------------------------------------------------------- */
        String mod = req.getModality().toLowerCase();

        switch (mod) {
            case "llm" -> {
                Map<String, Object> m = req.getMetrics();
                Map<String, Object> t = req.getTechnicalSpecs();
                LlmSpecs spec = LlmSpecs.of(
                        model,
                        getDouble(m.get("mmlu")),
                        getDouble(m.get("hellaswag")),
                        getDouble(m.get("arc")),
                        getDouble(m.get("truthfulqa")),
                        getDouble(m.get("gsm8k")),
                        getDouble(m.get("humaneval")),
                        getString(t.get("context_window")),
                        getInt(t.get("max_output_tokens")),
                        getString(req.getSample().get("sample_output"))
                );
                llmSpecsRepository.save(spec);
            }
            case "image_generation", "image" -> {
                Map<String, Object> m = req.getMetrics();
                ImageSpecs spec = ImageSpecs.of(
                        model,
                        getDouble(m.get("fid")),
                        getDouble(m.get("inception_score")),
                        getDouble(m.get("clip_score")),
                        getInt(m.get("prompt_tokens")),
                        getString(m.get("max_output_resolution")),
                        getString(req.getSample().get("sample_prompt")),
                        getString(req.getSample().get("sample_output_image"))
                );
                imageSpecsRepository.save(spec);
            }
            case "audio" -> {
                Map<String, Object> m = req.getMetrics();
                AudioSpecs spec = AudioSpecs.of(
                        model,
                        getDouble(m.get("wer_ko")),
                        getDouble(m.get("mos")),
                        getDouble(m.get("latency")),
                        getString(m.get("max_audio_input")),
                        getString(m.get("max_audio_output")),
                        getString(m.get("sample_rate")),
                        getString(req.getSample().get("sample_input_audio")),
                        getString(req.getSample().get("sample_output"))
                );
                audioSpecsRepository.save(spec);
            }
            case "multimodal" -> {
                Map<String, Object> m = req.getMetrics();
                Map<String, Object> t = req.getTechnicalSpecs();
                MultimodalSpecs spec = MultimodalSpecs.of(
                        model,
                        getDouble(m.get("mme")),
                        getDouble(m.get("ocr_f1")),
                        getDouble(m.get("vqav2")),
                        getString(t.get("text_tokens")),
                        getInt(t.get("max_images")),
                        getString(t.get("max_image_resolution")),
                        getString(req.getSample().get("sample_input_image")),
                        getString(req.getSample().get("sample_prompt")),
                        getString(req.getSample().get("sample_output"))
                );
                multimodalSpecsRepository.save(spec);
            }
        }

        return model.getId();
    }

    /* =============================================================
       🔹 유틸
       ============================================================= */
    private String writeJson(Object obj) {
        if (obj == null) return "[]";
        try { return objectMapper.writeValueAsString(obj); }
        catch (Exception e) { return "[]"; }
    }

    private Double getDouble(Object obj) {
        if (obj == null) return null;
        if (obj instanceof Number n) return n.doubleValue();
        try { return Double.parseDouble(obj.toString()); } catch (Exception e) { return null; }
    }

    private Integer getInt(Object obj) {
        if (obj == null) return null;
        if (obj instanceof Number n) return n.intValue();
        try { return Integer.parseInt(obj.toString()); } catch (Exception e) { return null; }
    }

    private String getString(Object obj) {
        if (obj == null) return null;
        String s = obj.toString();
        return s.isBlank() ? null : s;
    }
}
