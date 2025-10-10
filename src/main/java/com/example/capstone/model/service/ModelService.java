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

    private final BlockchainClient blockchainClient;
    private final BlockchainService blockchainService;

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

        // 모달리티별 metrics / specs / sample
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

        // Pricing 조립
        Map<String, Object> pricingMap = buildPricingMap(model);

        // Lineage 조립
        List<Map<String,Object>> lineageList = model.getLineage().stream().map(l -> {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("step", l.getStep());
            map.put("from", l.getFromModel());
            map.put("to", l.getToModel());
            map.put("relationship", l.getRelationship().name().toLowerCase());
            return map;
        }).collect(Collectors.toList());

        // ReleaseNotes 조립
        List<String> releaseNotesList = model.getReleaseNotes().stream()
                .map(ReleaseNote::getNote)
                .toList();

        // 최종 DTO
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

    /** 리스트용 DTO (pricing + metrics 포함) */
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

    /** Pricing 공통 로직 */
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

    /** License 문자열 파싱 */
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

    /** 모델 업로드 */
    @Transactional
    public Long uploadModel(ModelUploadRequest req) {

        // ✅ 부모 모델 PDA 찾기 (있으면 설정)
        String parentModelPda = null;
        if (req.getParentModelId() != null) {
            modelRepository.findById(Long.valueOf(req.getParentModelId()))
                    .ifPresent(parent -> {
                        // 캡처 변수에 대입
                    });
            parentModelPda = modelRepository.findById(Long.valueOf(req.getParentModelId()))
                    .map(Model::getPda)
                    .orElse(null);
        }

        // ✅ 블록체인 등록 요청
        BlockchainRequest chainReq = BlockchainRequest.builder()
                .developerWallet(req.getWalletAddress())
                .developerSignature(req.getDeveloperSignature()) // optional
                .modelName(req.getName())
                .ipfsCid(req.getCidRoot())
                .priceLamports("100000000") // TODO: pricing에서 변환
                .royaltyBps(250)
                .parentModelPda(parentModelPda)
                .build();

        BlockchainResponse chainRes = blockchainService.registerOnChain(chainReq);

        // ✅ Model 엔티티 저장 (encryptionKey 평문 저장 전제)
        Model model = Model.builder()
                .name(req.getName())
                .uploader(req.getUploader() != null ? req.getUploader() : req.getWalletAddress())
                .versionName(req.getVersionName())
                .modality(Modality.valueOf(req.getModality().toUpperCase()))
                .license(req.getLicense() != null ? req.getLicense().toString() : "[]")
                .overview(req.getOverview())
                .releaseDate(req.getReleaseDate())
                .thumbnail(req.getThumbnail())
                .cidRoot(req.getCidRoot())
                .encryptionKey(req.getEncryptionKey())     // <-- Model 엔티티에 필드 존재해야 함
                .pda(chainRes.getPda())
                .onchainTx(chainRes.getTxSignature())
                .build();

        modelRepository.save(model);

        // ✅ NPE 방지를 위한 맵 기본값
        Map<String, Object> metrics = Optional.ofNullable(req.getMetrics()).orElseGet(HashMap::new);
        Map<String, Object> tech    = Optional.ofNullable(req.getTechnicalSpecs()).orElseGet(HashMap::new);
        Map<String, String> sample  = Optional.ofNullable(req.getSample()).orElseGet(HashMap::new);

        // ✅ 모달리티별 세부 스펙 저장
        switch (model.getModality()) {
            case LLM -> {
                LlmSpecs spec = LlmSpecs.builder()
                        .model(model)
                        .modelId(model.getId())
                        .mmlu(getDouble(metrics.get("MMLU")))
                        .hellaswag(getDouble(metrics.get("HellaSwag")))
                        .arc(getDouble(metrics.get("ARC")))
                        .truthfulqa(getDouble(metrics.get("TruthfulQA")))
                        .gsm8k(getDouble(metrics.get("GSM8K")))
                        .humaneval(getDouble(metrics.get("HumanEval")))
                        .contextWindow(getString(tech.get("contextWindow")))
                        .maxOutputTokens(getInt(tech.get("maxOutputTokens")))
                        .sampleOutput(getString(sample.get("output")))
                        .build();
                llmSpecsRepository.save(spec);
            }
            case IMAGE_GENERATION -> {
                ImageSpecs spec = ImageSpecs.builder()
                        .model(model)
                        .modelId(model.getId())
                        .fid(getDouble(metrics.get("FID")))
                        .inceptionScore(getDouble(metrics.get("InceptionScore")))
                        .clipScore(getDouble(metrics.get("CLIPScore")))
                        .promptTokens(getInt(tech.get("promptTokens")))
                        .maxOutputResolution(getString(tech.get("maxOutputResolution")))
                        .samplePrompt(getString(sample.get("prompt")))
                        .sampleOutputImage(getString(sample.get("outputImage"))) // S3 URL
                        .build();
                imageSpecsRepository.save(spec);
            }
            case AUDIO -> {
                AudioSpecs spec = AudioSpecs.builder()
                        .model(model)
                        .modelId(model.getId())
                        .werKo(getDouble(metrics.get("WER_KO")))
                        .mos(getDouble(metrics.get("MOS")))
                        .latency(getDouble(metrics.get("Latency")))
                        .sampleInputAudio(getString(sample.get("inputAudio")))   // S3 URL
                        .sampleOutput(getString(sample.get("output")))
                        .build();
                audioSpecsRepository.save(spec);
            }
            case MULTIMODAL -> {
                MultimodalSpecs spec = MultimodalSpecs.builder()
                        .model(model)
                        .modelId(model.getId())
                        .mme(getDouble(metrics.get("MME")))
                        .ocrF1(getDouble(metrics.get("OCR_F1")))
                        .vqav2(getDouble(metrics.get("VQAv2")))
                        .samplePrompt(getString(sample.get("prompt")))
                        .sampleInputImage(getString(sample.get("inputImage")))   // S3 URL
                        .sampleOutput(getString(sample.get("output")))
                        .build();
                multimodalSpecsRepository.save(spec);
            }
        }

        return model.getId();
    }

    /* -------- 변환 유틸 -------- */

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
