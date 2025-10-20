package com.example.capstone.model.service;

import com.example.capstone.blockchain.client.BlockchainClient;
import com.example.capstone.blockchain.dto.BlockchainRequest;
import com.example.capstone.blockchain.dto.BlockchainResponse;
import com.example.capstone.blockchain.service.BlockchainService;
import com.example.capstone.model.dto.ModelDetailResponse;
import com.example.capstone.model.dto.ModelListResponse;
import com.example.capstone.model.dto.ModelUploadRequest;
import com.example.capstone.model.entity.*;
import com.example.capstone.model.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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

    private final BlockchainClient blockchainClient;
    private final BlockchainService blockchainService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    /** 전체 모델 조회 */
    public List<ModelListResponse> getAllModels() {
        return modelRepository.findAll().stream()
                .map(this::toListDto)
                .collect(Collectors.toList());
    }

    /** 특정 모델 상세 조회 */
    public ModelDetailResponse getModelDetail(Long id) {
        Model model = modelRepository.findDetailById(id)
                .orElseThrow(() -> new IllegalArgumentException("모델을 찾을 수 없습니다. id=" + id));

        Map<String, Object> metrics = new LinkedHashMap<>();
        Map<String, Object> technicalSpecs = new LinkedHashMap<>();
        Object sample = null;

        switch (model.getModality()) {
            case LLM -> {
                LlmSpecs spec = llmSpecsRepository.findById(id).orElse(null);
                if (spec != null) {
                    metrics.put("MMLU", spec.getMmlu());
                    metrics.put("HellaSwag", spec.getHellaswag());
                    metrics.put("ARC", spec.getArc());
                    metrics.put("TruthfulQA", spec.getTruthfulqa());
                    metrics.put("GSM8K", spec.getGsm8k());
                    metrics.put("HumanEval", spec.getHumaneval());
                    technicalSpecs.put("contextWindow", spec.getContextWindow());
                    technicalSpecs.put("maxOutputTokens", spec.getMaxOutputTokens());
                    sample = spec.getSampleOutput();
                }
            }
            case IMAGE_GENERATION -> {
                ImageSpecs spec = imageSpecsRepository.findById(id).orElse(null);
                if (spec != null) {
                    metrics.put("FID", spec.getFid());
                    metrics.put("InceptionScore", spec.getInceptionScore());
                    metrics.put("CLIPScore", spec.getClipScore());
                    technicalSpecs.put("promptTokens", spec.getPromptTokens());
                    technicalSpecs.put("maxOutputResolution", spec.getMaxOutputResolution());
                    sample = Map.of(
                            "prompt", spec.getSamplePrompt(),
                            "outputImage", spec.getSampleOutputImage()
                    );
                }
            }
            case AUDIO -> {
                AudioSpecs spec = audioSpecsRepository.findById(id).orElse(null);
                if (spec != null) {
                    metrics.put("WER_KO", spec.getWerKo());
                    metrics.put("MOS", spec.getMos());
                    metrics.put("Latency", spec.getLatency());
                    technicalSpecs.put("maxAudioInput", spec.getMaxAudioInput());
                    technicalSpecs.put("maxAudioOutput", spec.getMaxAudioOutput());
                    technicalSpecs.put("sampleRate", spec.getSampleRate());
                    sample = Map.of(
                            "inputAudio", spec.getSampleInputAudio(),
                            "output", spec.getSampleOutput()
                    );
                }
            }
            case MULTIMODAL -> {
                MultimodalSpecs spec = multimodalSpecsRepository.findById(id).orElse(null);
                if (spec != null) {
                    metrics.put("MME", spec.getMme());
                    metrics.put("OCR_F1", spec.getOcrF1());
                    metrics.put("VQAv2", spec.getVqav2());
                    technicalSpecs.put("textTokens", spec.getTextTokens());
                    technicalSpecs.put("maxImages", spec.getMaxImages());
                    technicalSpecs.put("maxImageResolution", spec.getMaxImageResolution());
                    sample = Map.of(
                            "inputImage", spec.getSampleInputImage(),
                            "prompt", spec.getSamplePrompt(),
                            "output", spec.getSampleOutput()
                    );
                }
            }
        }

        Map<String, Object> pricingMap = buildPricingMap(model);

        List<Map<String, Object>> lineageList = model.getLineage().stream().map(l -> {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("step", l.getStep());
            map.put("from", l.getFromModel());
            map.put("to", l.getToModel());
            map.put("relationship", l.getRelationship().name().toLowerCase());
            return map;
        }).collect(Collectors.toList());

        List<String> releaseNotesList = model.getReleaseNotes().stream()
                .map(ReleaseNote::getNote)
                .toList();

        return ModelDetailResponse.builder()
                .id(model.getId())
                .name(model.getName())
                .uploader(model.getUploader())
                .versionName(model.getVersionName())
                .modality(model.getModality().name())
                .license(parseLicense(model.getLicense()))
                .releaseDate(model.getReleaseDate())
                .overview(model.getOverview())
                .compliance(model.getCompliance())
                .cidRoot(model.getCidRoot())
                .checksumRoot(model.getChecksumRoot())
                .onchainTx(model.getOnchainTx())
                .thumbnail(model.getThumbnail())
                .pricing(pricingMap)
                .metrics(metrics)
                .technicalSpecs(technicalSpecs)
                .samples(sample)
                .lineage(lineageList)
                .releaseNotes(releaseNotesList)
                .build();
    }

    private ModelListResponse toListDto(Model m) {
        Map<String, Object> pricingMap = buildPricingMap(m);
        Map<String, Object> metrics = new LinkedHashMap<>();
        switch (m.getModality()) {
            case LLM -> {
                LlmSpecs spec = llmSpecsRepository.findById(m.getId()).orElse(null);
                if (spec != null) {
                    metrics.put("MMLU", spec.getMmlu());
                    metrics.put("HellaSwag", spec.getHellaswag());
                    metrics.put("ARC", spec.getArc());
                    metrics.put("TruthfulQA", spec.getTruthfulqa());
                    metrics.put("GSM8K", spec.getGsm8k());
                    metrics.put("HumanEval", spec.getHumaneval());
                }
            }
            case IMAGE_GENERATION -> {
                ImageSpecs spec = imageSpecsRepository.findById(m.getId()).orElse(null);
                if (spec != null) {
                    metrics.put("FID", spec.getFid());
                    metrics.put("InceptionScore", spec.getInceptionScore());
                    metrics.put("CLIPScore", spec.getClipScore());
                }
            }
            case AUDIO -> {
                AudioSpecs spec = audioSpecsRepository.findById(m.getId()).orElse(null);
                if (spec != null) {
                    metrics.put("WER_KO", spec.getWerKo());
                    metrics.put("MOS", spec.getMos());
                    metrics.put("Latency", spec.getLatency());
                }
            }
            case MULTIMODAL -> {
                MultimodalSpecs spec = multimodalSpecsRepository.findById(m.getId()).orElse(null);
                if (spec != null) {
                    metrics.put("MME", spec.getMme());
                    metrics.put("OCR_F1", spec.getOcrF1());
                    metrics.put("VQAv2", spec.getVqav2());
                }
            }
        }

        return ModelListResponse.builder()
                .id(m.getId())
                .name(m.getName())
                .uploader(m.getUploader())
                .versionName(m.getVersionName())
                .modality(m.getModality().name())
                .license(parseLicense(m.getLicense()))
                .releaseDate(m.getReleaseDate())
                .thumbnail(m.getThumbnail())
                .pricing(pricingMap.isEmpty() ? null : pricingMap)
                .metrics(metrics.isEmpty() ? null : metrics)
                .build();
    }

    private Map<String, Object> buildPricingMap(Model model) {
        Map<String, Object> pricingMap = new LinkedHashMap<>();
        for (PricingPlan plan : model.getPricingPlans()) {
            Map<String, Object> planMap = new LinkedHashMap<>();
            planMap.put("price", plan.getPrice());
            planMap.put("description", plan.getDescription());
            planMap.put("billingType", plan.getBillingType().name().toLowerCase());

            if (plan.getMonthlyTokenLimit() != null) planMap.put("monthlyTokenLimit", plan.getMonthlyTokenLimit());
            if (plan.getMonthlyGenerationLimit() != null) planMap.put("monthlyGenerationLimit", plan.getMonthlyGenerationLimit());
            if (plan.getMonthlyRequestLimit() != null) planMap.put("monthlyRequestLimit", plan.getMonthlyRequestLimit());

            if (plan.getRights() != null) {
                List<String> rightsList = Arrays.stream(plan.getRights()
                                .replace("[", "")
                                .replace("]", "")
                                .replace("\"", "")
                                .split(","))
                        .map(String::trim)
                        .filter(s -> !s.isBlank())
                        .toList();
                planMap.put("rights", rightsList);
            }

            pricingMap.put(plan.getPlanType().name().toLowerCase(), planMap);
        }
        return pricingMap;
    }

    private List<String> parseLicense(String licenseStr) {
        if (licenseStr == null) return List.of();
        return Arrays.stream(licenseStr
                        .replace("[", "")
                        .replace("]", "")
                        .replace("\"", "")
                        .split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();
    }

    /** ✅ 업로드 (엔티티 구조 반영 버전) */
    @Transactional
    public Long uploadModel(ModelUploadRequest req) {
        // lineage 정보
        String parentModelId = req.getLineage() != null ? req.getLineage().getParentModelId() : null;
        String relationship = req.getLineage() != null ? req.getLineage().getRelationship() : null;

        String parentModelPda = null;
        if (parentModelId != null) {
            parentModelPda = modelRepository.findById(Long.valueOf(parentModelId))
                    .map(Model::getPda).orElse(null);
        }

        BlockchainRequest chainReq = BlockchainRequest.builder()
                .developerWallet(req.getWalletAddress())
                .developerSignature(req.getDeveloperSignature())
                .modelName(req.getName())
                .ipfsCid(req.getCidRoot())
                .priceLamports("100000000")
                .royaltyBps(250)
                .parentModelPda(parentModelPda)
                .build();

        BlockchainResponse chainRes = blockchainService.registerOnChain(chainReq);

        String licenseJson = "[]";
        try {
            if (req.getLicense() != null && !req.getLicense().isEmpty()) {
                licenseJson = objectMapper.writeValueAsString(req.getLicense());
            }
        } catch (Exception ignored) {}

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

        // lineage 저장
        if (parentModelId != null || relationship != null) {
            String fromModel = modelRepository.findById(Long.valueOf(parentModelId))
                    .map(Model::getName).orElse(null);

            Relationship relationEnum;
            try {
                relationEnum = Relationship.valueOf(relationship.trim().toUpperCase().replace("-", "_"));
            } catch (Exception e) {
                relationEnum = Relationship.iteration;
            }

            Integer currentMax = lineageRepository.findMaxStepByModelId(model.getId());
            int nextStep = (currentMax == null ? 0 : currentMax) + 1;

            lineageRepository.save(Lineage.builder()
                    .model(model)
                    .step(nextStep)
                    .fromModel(fromModel)
                    .toModel(model.getName())
                    .relationship(relationEnum)
                    .build());
        }

        // release note
        if (req.getReleaseNotes() != null) {
            releaseNoteRepository.save(ReleaseNote.builder()
                    .model(model)
                    .note(req.getReleaseNotes())
                    .build());
        }

        // pricing plan
        if (req.getPricing() != null) {
            for (Map.Entry<String, Object> entry : req.getPricing().entrySet()) {
                Map<String, Object> plan = (Map<String, Object>) entry.getValue();
                pricingPlanRepository.save(PricingPlan.builder()
                        .model(model)
                        .planType(PlanType.valueOf(entry.getKey().toUpperCase()))
                        .price(getDouble(plan.get("price")))
                        .description(getString(plan.get("description")))
                        .billingType(BillingType.valueOf(getString(plan.get("billingType")).toUpperCase()))
                        .rights(writeJson(plan.get("rights")))
                        .monthlyTokenLimit(getInt(plan.get("monthlyTokenLimit")))
                        .monthlyGenerationLimit(getInt(plan.get("monthlyGenerationLimit")))
                        .monthlyRequestLimit(getInt(plan.get("monthlyRequestLimit")))
                        .build());
            }
        }

        // specs 저장
        switch (req.getModality().toLowerCase()) {
            case "llm" -> llmSpecsRepository.save(LlmSpecs.of(
                    model,
                    getDouble(req.getMetrics().get("mmlu")),
                    getDouble(req.getMetrics().get("hellaswag")),
                    getDouble(req.getMetrics().get("arc")),
                    getDouble(req.getMetrics().get("truthfulqa")),
                    getDouble(req.getMetrics().get("gsm8k")),
                    getDouble(req.getMetrics().get("humaneval")),
                    getString(req.getTechnicalSpecs().get("context_window")),
                    getInt(req.getTechnicalSpecs().get("max_output_tokens")),
                    getString(req.getSample().get("sample_output"))
            ));
            case "image_generation", "image" -> imageSpecsRepository.save(ImageSpecs.of(
                    model,
                    getDouble(req.getMetrics().get("fid")),
                    getDouble(req.getMetrics().get("inception_score")),
                    getDouble(req.getMetrics().get("clip_score")),
                    getInt(req.getTechnicalSpecs().get("prompt_tokens")),
                    getString(req.getTechnicalSpecs().get("max_output_resolution")),
                    getString(req.getSample().get("sample_prompt")),
                    getString(req.getSample().get("sample_output_image"))
            ));
            case "audio" -> audioSpecsRepository.save(AudioSpecs.of(
                    model,
                    getDouble(req.getMetrics().get("wer_ko")),
                    getDouble(req.getMetrics().get("mos")),
                    getDouble(req.getMetrics().get("latency")),
                    getString(req.getTechnicalSpecs().get("max_audio_input")),
                    getString(req.getTechnicalSpecs().get("max_audio_output")),
                    getString(req.getTechnicalSpecs().get("sample_rate")),
                    getString(req.getSample().get("sample_input_audio")),
                    getString(req.getSample().get("sample_output"))
            ));
            case "multimodal" -> multimodalSpecsRepository.save(MultimodalSpecs.of(
                    model,
                    getDouble(req.getMetrics().get("mme")),
                    getDouble(req.getMetrics().get("ocr_f1")),
                    getDouble(req.getMetrics().get("vqav2")),
                    getString(req.getTechnicalSpecs().get("text_tokens")),
                    getInt(req.getTechnicalSpecs().get("max_images")),
                    getString(req.getTechnicalSpecs().get("max_image_resolution")),
                    getString(req.getSample().get("sample_input_image")),
                    getString(req.getSample().get("sample_prompt")),
                    getString(req.getSample().get("sample_output"))
            ));
        }

        return model.getId();
    }

    /* -------- 변환 유틸 -------- */
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
