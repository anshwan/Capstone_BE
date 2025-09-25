package com.example.capstone.model.service;

import com.example.capstone.model.dto.ModelDetailDto;
import com.example.capstone.model.dto.ModelSummaryDto;
import com.example.capstone.model.entity.Model;
import com.example.capstone.model.entity.ModelVersion;
import com.example.capstone.model.repository.ModelRepository;
import com.example.capstone.model.repository.ModelVersionRepository;
import com.example.capstone.user.entity.AppUser;
import com.example.capstone.user.repository.AppUserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ModelService {

    private final ModelRepository modelRepository;
    private final ModelVersionRepository modelVersionRepository;
    private final AppUserRepository appUserRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 전체 모델 조회
     * - 모델 기본 정보 + 최신 버전 정보만 포함
     */
    @Transactional(readOnly = true)
    public List<ModelSummaryDto> getAllModels() {
        List<Model> models = modelRepository.findAll();
        List<ModelSummaryDto> result = new ArrayList<>();

        for (Model model : models) {
            List<ModelVersion> versions = modelVersionRepository.findByModelIdWithRelations(model.getId());
            if (!versions.isEmpty()) {
                ModelVersion latest = versions.get(0);

                AppUser user = appUserRepository.findById(model.getCreatedBy()).orElse(null);

                Map<String, Object> metricsMap = parseJsonToMap(latest.getMetricsJson());

                result.add(new ModelSummaryDto(
                        model.getId(),
                        model.getName(),
                        (user != null ? user.getName() : "User-" + model.getCreatedBy()),
                        latest.getModality().getName(),
                        latest.getLicense().getName(),
                        latest.getCurrency(),
                        latest.getPriceStandard() != null ? latest.getPriceStandard().doubleValue() : null,
                        metricsMap
                ));
            }
        }
        return result;
    }

    /**
     * 모델 상세 조회
     */
    @Transactional(readOnly = true)
    public ModelDetailDto getModelDetail(Long modelId) {
        Model model = modelRepository.findById(modelId)
                .orElseThrow(() -> new RuntimeException("모델을 찾을 수 없습니다."));

        List<ModelVersion> versions = modelVersionRepository.findByModelIdWithRelations(modelId);
        if (versions.isEmpty()) {
            throw new RuntimeException("모델 버전을 찾을 수 없습니다.");
        }
        ModelVersion version = versions.get(0);

        AppUser user = appUserRepository.findById(model.getCreatedBy()).orElse(null);

        return new ModelDetailDto(
                model.getId(),
                model.getName(),
                version.getVersionName(),
                version.getStatus(),
                (user != null ? user.getName() : "User-" + model.getCreatedBy()),
                version.getModality().getName(),
                version.getLicense().getName(),
                version.getCurrency(),
                version.getPriceResearch(),
                version.getPriceStandard(),
                version.getPriceEnterprise(),
                version.getOverview(),
                parseJsonToMap(version.getMetricsJson()),
                parseJsonToList(version.getSamplesJson()),
                parseJsonToList(version.getLineageJson()),
                version.getReleaseNotes(),
                version.getReleaseDate(),
                version.getCidRoot(),
                version.getChecksumRoot(),
                version.getOnchainTx()
        );
    }

    /**
     * 모델 필터링 조회
     */
    @Transactional(readOnly = true)
    public List<ModelSummaryDto> filterModels(
            String modality,
            String license,
            Double maxPrice,
            Double minPerformance
    ) {
        List<ModelSummaryDto> allModels = getAllModels();

        return allModels.stream()
                .filter(dto -> modality == null || dto.getModality().equalsIgnoreCase(modality))
                .filter(dto -> license == null || dto.getLicense().equalsIgnoreCase(license))
                .filter(dto -> maxPrice == null || dto.getPriceStandard() == null || dto.getPriceStandard() <= maxPrice)
                .filter(dto -> {
                    if (minPerformance == null || dto.getMetrics() == null) return true;
                    OptionalDouble min = dto.getMetrics().values().stream()
                            .mapToDouble(v -> Double.parseDouble(v.toString()))
                            .min();
                    return min.isPresent() && min.getAsDouble() >= minPerformance;
                })
                .collect(Collectors.toList());
    }

    /**
     * JSON 문자열 → Map 변환
     * 실패하면 원문 그대로 담아서 반환
     */
    private Map<String, Object> parseJsonToMap(String json) {
        if (json == null) return null;
        try {
            return objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {});
        } catch (Exception e) {
            return Map.of("raw", json); // ✅ 원문 반환
        }
    }

    /**
     * JSON 문자열 → List 변환
     * 실패하면 원문 그대로 리스트로 반환
     */
    private List<Object> parseJsonToList(String json) {
        if (json == null) return null;
        try {
            return objectMapper.readValue(json, new TypeReference<List<Object>>() {});
        } catch (Exception e) {
            return List.of(json); // ✅ 원문 반환
        }
    }
}
