package com.example.capstone.model.service;

import com.example.capstone.auth.jwt.JwtUserPrincipal;
import com.example.capstone.model.dto.ModelUploadRequest;
import com.example.capstone.model.dto.ModelUploadResponse;
import com.example.capstone.model.entity.*;
import com.example.capstone.model.repository.*;
import com.example.capstone.user.entity.AppUser;
import com.example.capstone.user.repository.AppUserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ModelUploadService {

    private final ModelRepository modelRepository;
    private final ModelVersionRepository modelVersionRepository;
    private final ModalityRepository modalityRepository;
    private final LicenseRepository licenseRepository;
    private final AppUserRepository appUserRepository;

    @Transactional
    public ModelUploadResponse uploadModel(ModelUploadRequest req) {
        // 1. 로그인 유저 정보 가져오기
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();

        if (!(principal instanceof JwtUserPrincipal jwtUser)) {
            throw new IllegalStateException("Unexpected principal type: " + principal.getClass());
        }

        Long uploaderId = jwtUser.getId();

        // 2. AppUser 조회
        AppUser uploader = appUserRepository.findById(uploaderId)
                .orElseThrow(() -> new IllegalStateException("User not found with id " + uploaderId));

        // 3. 더미 온체인 tx (추후 실제 연결)
        String txSignature = "mock-tx-123";

        // 4. Model 생성 및 저장
        Model model = modelRepository.save(Model.builder()
                .name(req.getModelName())
                .createdBy(uploader.getId())
                .build());

        // 5. ModelVersion 생성 및 저장
        ModelVersion version = modelVersionRepository.save(ModelVersion.builder()
                .model(model)
                .versionName(req.getVersionName())
                .modality(modalityRepository.findByCode(req.getModalityCode()).orElseThrow())
                .license(licenseRepository.findByCode(req.getLicenseCode()).orElseThrow())
                .currency(req.getCurrency() != null ? req.getCurrency() : "USDC")
                .priceResearch(BigDecimal.valueOf(req.getPriceResearch()))
                .priceStandard(BigDecimal.valueOf(req.getPriceStandard()))
                .priceEnterprise(BigDecimal.valueOf(req.getPriceEnterprise()))
                .overview(req.getOverview())
                .releaseNotes(req.getReleaseNotes())
                .releaseDate(req.getReleaseDate())
                .cidRoot(req.getCidRoot())
                .checksumRoot(req.getChecksumRoot())
                .onchainTx(txSignature)
                .status(ModelVersionStatus.PUBLISHED)
                .metricsJson(toJson(req.getMetrics()))
                .samplesJson(toJson(req.getSamples()))
                .lineageJson(toJson(req.getLineage()))
                .storageJson(toJson(req.getStorage()))
                .uploader(uploader) // ✅ AppUser 넣으면 FK 자동 처리됨
                .build());

        // 6. 응답 반환
        return ModelUploadResponse.builder()
                .modelId(model.getId())
                .versionId(version.getId())
                .modelName(model.getName())
                .versionName(version.getVersionName())
                .onchainTx(txSignature)
                .ipfsCid(req.getCidRoot())
                .build();
    }

    private String toJson(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            return "{}";
        }
    }
}
