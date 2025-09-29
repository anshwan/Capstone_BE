// src/main/java/com/example/capstone/model/service/ModelUploadService.java
package com.example.capstone.model.service;

import com.example.capstone.auth.jwt.JwtUserPrincipal;
import com.example.capstone.model.dto.ModelUploadRequest;
import com.example.capstone.model.dto.ModelUploadResponse;
import com.example.capstone.model.entity.*;
import com.example.capstone.model.repository.*;
import com.example.capstone.onchain.client.BlockchainClient;
import com.example.capstone.onchain.dto.RegisterModelRequest;
import com.example.capstone.onchain.dto.RegisterModelResult;
import com.example.capstone.user.entity.AppUser;
import com.example.capstone.user.repository.AppUserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ModelUploadService {

    private final ModelRepository modelRepository;
    private final ModelVersionRepository modelVersionRepository;
    private final ModalityRepository modalityRepository;
    private final LicenseRepository licenseRepository;
    private final AppUserRepository appUserRepository;

    private final BlockchainClient blockchainClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Transactional
    public ModelUploadResponse uploadModel(JwtUserPrincipal user, ModelUploadRequest req) {
        // 1) 업로더 식별
        Long uploaderId = user.getId();
        AppUser uploader = appUserRepository.findById(uploaderId)
                .orElseThrow(() -> new IllegalStateException("User not found with id " + uploaderId));

        // 2) 온체인 register_model 호출 (실패하면 더미TX 반환)
        //    modelId는 온체인측 요구에 맞춰 문자열 사용(여기선 임시 GUID 부여)
        String preOnchainModelId = "model-" + UUID.randomUUID();

        RegisterModelRequest onchainReq = RegisterModelRequest.builder()
                .developerWallet(req.getDeveloperWallet())
                .developerSignature(req.getDeveloperSignature())
                .modelId(preOnchainModelId)
                .modelName(req.getModelName())
                .ipfsCid(req.getCidRoot())
                .priceLamports(req.getPriceLamports())
                .royaltyBps(req.getRoyaltyBps())
                .parentModel(req.getParentModel() != null ? req.getParentModel() : req.getLineage())
                .build();

        RegisterModelResult onchainRes = blockchainClient.registerModel(onchainReq);

        String txSignature = onchainRes.getTxSignature(); // 성공/실패 모두 값 존재(실패 시 mock)
        boolean onchainSucceeded = onchainRes.isSuccess();
        String onchainModelId = onchainRes.getOnchainModelId(); // 실패시 null

        // 3) DB 저장 (Model → ModelVersion)
        Model model = modelRepository.save(Model.builder()
                .name(req.getModelName())
                .createdBy(uploader.getId())
                .build());

        ModelVersion version = modelVersionRepository.save(ModelVersion.builder()
                .model(model)
                .versionName(req.getVersionName())
                .modality(modalityRepository.findByCode(req.getModalityCode())
                        .orElseThrow(() -> new IllegalArgumentException("Unknown modalityCode: " + req.getModalityCode())))
                .license(licenseRepository.findByCode(req.getLicenseCode())
                        .orElseThrow(() -> new IllegalArgumentException("Unknown licenseCode: " + req.getLicenseCode())))
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
                .status(ModelVersionStatus.PUBLISHED) // 필요 시 DRAFT -> 검수 -> PUBLISHED로 확장
                .metricsJson(toJson(req.getMetrics()))
                .samplesJson(toJson(req.getSamples()))
                .lineageJson(toJson(req.getLineage()))
                .storageJson(toJson(req.getStorage()))
                .accessJson(toJson(req.getAccess()))
                .ioLimitsJson(toJson(req.getIoLimits()))
                .uploader(uploader)
                .build());

        // 4) 응답 반환
        return ModelUploadResponse.builder()
                .modelId(model.getId())
                .versionId(version.getId())
                .modelName(model.getName())
                .versionName(version.getVersionName())
                .onchainTx(txSignature)
                .onchainModelId(onchainModelId)
                .onchainSucceeded(onchainSucceeded)
                .ipfsCid(req.getCidRoot())
                .build();
    }

    private String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            return "{}";
        }
    }
}
