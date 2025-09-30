package com.example.capstone.model.service;

import com.example.capstone.model.dto.ModelDetailDto;
import com.example.capstone.model.dto.ModelSummaryDto;
import com.example.capstone.model.entity.Model;
import com.example.capstone.model.entity.ModelVersion;
import com.example.capstone.model.repository.ModelRepository;
import com.example.capstone.model.repository.ModelVersionRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ModelService {

    private final ModelRepository modelRepository;
    private final ModelVersionRepository modelVersionRepository;

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
                ModelVersion latest = versions.get(0); // 최신 버전이라고 가정

                result.add(ModelSummaryDto.builder()
                        .id(model.getId())
                        .name(model.getName())
                        .uploader(model.getUploader())
                        .versionName(latest.getVersionName())
                        .modality(latest.getModality().getCode())
                        .license(parseJsonToList(latest.getLicenseJson()).stream().map(Object::toString).toList())
                        .releaseDate(latest.getReleaseDate() != null ? latest.getReleaseDate().toString() : null)
                        .pricing(parseJsonToMap(latest.getPricingJson()))
                        .metrics(parseJsonToMap(latest.getMetricsJson()))
                        .thumbnail(model.getThumbnail())
                        .build()
                );
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

        return ModelDetailDto.builder()
                .id(model.getId())
                .name(model.getName())
                .uploader(model.getUploader())
                .versionName(version.getVersionName())
                .modality(version.getModality().getCode())
                .license(parseJsonToList(version.getLicenseJson()).stream().map(Object::toString).toList())
                .releaseDate(version.getReleaseDate() != null ? version.getReleaseDate().toString() : null)
                .overview(version.getOverview())
                .pricing(parseJsonToMap(version.getPricingJson()))
                .metrics(parseJsonToMap(version.getMetricsJson()))
                .technicalSpecs(parseJsonToMap(version.getTechnicalSpecsJson()))
                .compliance(model.getCompliance())
                .samples(parseJsonToList(version.getSamplesJson()))
                .lineage(parseJsonToList(version.getLineageJson()))
                .releaseNotes(parseJsonToList(version.getReleaseNotesJson()))
                .cidRoot(version.getCidRoot())
                .checksumRoot(version.getChecksumRoot())
                .onchainTx(version.getOnchainTx())
                .thumbnail(model.getThumbnail())
                .build();
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
            return Map.of("raw", json);
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
            return List.of(json);
        }
    }
}
