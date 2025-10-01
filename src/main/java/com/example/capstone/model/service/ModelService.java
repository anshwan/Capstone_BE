package com.example.capstone.model.service;

import com.example.capstone.model.dto.ModelDetailResponse;
import com.example.capstone.model.dto.ModelListResponse;
import com.example.capstone.model.entity.*;
import com.example.capstone.model.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ModelService {
    private final ModelRepository modelRepository;
    private final LlmSpecsRepository llmSpecsRepository;
    private final ImageSpecsRepository imageSpecsRepository;
    private final AudioSpecsRepository audioSpecsRepository;
    private final MultimodalSpecsRepository multimodalSpecsRepository;

    /** 전체 모델 조회 */
    public List<ModelListResponse> getAllModels() {
        return modelRepository.findAll().stream()
                .map(this::toListDto)
                .collect(Collectors.toList());
    }

    /** 상세 조회 */
    public ModelDetailResponse getModelDetail(Long id) {
        Model model = modelRepository.findDetailById(id)
                .orElseThrow(() -> new IllegalArgumentException("모델을 찾을 수 없습니다. id=" + id));

        // 모달리티별 metrics / specs / sample
        Map<String, Object> metrics = new HashMap<>();
        Map<String, Object> technicalSpecs = new HashMap<>();
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

        // ReleaseNotes (note string만 반환)
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
                .sample(sample)
                .lineage(lineageList)
                .releaseNotes(releaseNotesList)
                .build();
    }

    /** 리스트용 DTO (pricing + metrics 풀로 넣음) */
    private ModelListResponse toListDto(Model m) {
        // Pricing
        Map<String, Object> pricingMap = buildPricingMap(m);

        // Metrics (전체 조회에서도 풀 metrics 반환)
        Map<String, Object> metrics = new HashMap<>();
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
        Map<String, Object> pricingMap = new HashMap<>();
        for (PricingPlan plan : model.getPricingPlans()) {
            Map<String, Object> planMap = new HashMap<>();
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
}
