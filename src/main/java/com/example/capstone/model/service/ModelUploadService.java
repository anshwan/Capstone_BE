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

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ModelUploadService {

    private final ModelRepository modelRepository;
    private final ModelVersionRepository modelVersionRepository;
    private final ModalityRepository modalityRepository;
    private final AppUserRepository appUserRepository;

    private final BlockchainClient blockchainClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Transactional
    public ModelUploadResponse uploadModel(JwtUserPrincipal user, ModelUploadRequest req) {
        // 1) 업로더 확인
        Long uploaderId = user.getId();
        AppUser uploader = appUserRepository.findById(uploaderId)
                .orElseThrow(() -> new IllegalStateException("User not found with id " + uploaderId));

        // 2) 온체인 등록 요청
        String preOnchainModelId = "model-" + UUID.randomUUID();

        RegisterModelRequest onchainReq = RegisterModelRequest.builder()
                .developerWallet(req.getDeveloperWallet())
                .developerSignature(req.getDeveloperSignature())
                .modelId(preOnchainModelId)
                .modelName(req.getModelName())
                .ipfsCid(req.getCidRoot())
                .priceLamports(req.getPriceLamports())
                .royaltyBps(req.getRoyaltyBps())
                .parentModel(req.getParentModel())
                .build();

        RegisterModelResult onchainRes = blockchainClient.registerModel(onchainReq);

        String txSignature = onchainRes.getTxSignature();
        boolean onchainSucceeded = onchainRes.isSuccess();
        String onchainModelId = onchainRes.getOnchainModelId();

        // 3) DB 저장
        Model model = modelRepository.save(Model.builder()
                .name(req.getModelName())
                .uploader(uploader.getName())   // 로그인한 사용자(AppUser) 이름 사용
                .thumbnail(req.getThumbnail())
                .compliance(req.getCompliance())
                .createdBy(uploader.getId())
                .build());


        ModelVersion version = modelVersionRepository.save(ModelVersion.builder()
                .model(model)
                .versionName(req.getVersionName())
                .modality(modalityRepository.findByCode(req.getModalityCode())
                        .orElseThrow(() -> new IllegalArgumentException("Unknown modalityCode: " + req.getModalityCode())))
                .overview(req.getOverview())
                .releaseDate(req.getReleaseDate())
                .cidRoot(req.getCidRoot())
                .checksumRoot(req.getChecksumRoot())
                .onchainTx(txSignature)
                .status(ModelVersionStatus.PUBLISHED)
                .licenseJson(toJson(req.getLicense()))
                .pricingJson(toJson(req.getPricing()))
                .metricsJson(toJson(req.getMetrics()))
                .samplesJson(toJson(req.getSamples()))
                .lineageJson(toJson(req.getLineage()))
                .technicalSpecsJson(toJson(req.getTechnicalSpecs()))
                .releaseNotesJson(toJson(req.getReleaseNotes()))
                .uploader(uploader)
                .build());

        // 4) 응답
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
