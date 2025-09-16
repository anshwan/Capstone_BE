package com.example.capstone.model.service;

import com.example.capstone.model.dto.ModelCreateRequest;
import com.example.capstone.model.dto.ModelCreatedResponse;
import com.example.capstone.model.entity.*;
import com.example.capstone.model.repository.*;
import com.example.capstone.user.entity.AppUser;
import com.example.capstone.user.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor
public class ModelService {

    private final ModelRepository modelRepo;
    private final ModelVersionRepository versionRepo;
    private final ModalityRepository modalityRepo;
    private final LicenseDefRepository licenseRepo;
    private final AppUserRepository userRepo;

    @Transactional
    public ModelCreatedResponse create(Long currentUserId, ModelCreateRequest req) {
        AppUser user = userRepo.findById(currentUserId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + currentUserId));

        // 모델 생성 (같은 사람이 같은 이름으로 계속 만들 수 있게 하되, 필요하면 exists 체크)
        Model model = Model.builder()
                .name(req.modelName())
                .createdBy(user)
                .build();
        model = modelRepo.save(model);

        Modality modality = modalityRepo.findByCode(req.modalityCode())
                .orElseThrow(() -> new IllegalArgumentException("Invalid modality code: " + req.modalityCode()));
        LicenseDef license = licenseRepo.findByCode(req.licenseCode())
                .orElseThrow(() -> new IllegalArgumentException("Invalid license code: " + req.licenseCode()));

        ModelVersion mv = ModelVersion.builder()
                .model(model)
                .versionName(req.versionName())
                .status(ModelVersionStatus.PUBLISHED)
                .modality(modality)
                .license(license)
                .uploader(user)
                .overview(req.overview())
                .releaseNotes(req.releaseNotes())
                .releaseDate(req.releaseDate())
                .cidRoot(req.cidRoot())
                .checksumRoot(req.checksumRoot())
                .onchainTx(req.onchainTx())
                .storageJson(req.storage())
                .metricsJson(req.metrics())
                .samplesJson(req.samples())
                .lineageJson(req.lineage())
                .build();

        mv = versionRepo.save(mv);

        return new ModelCreatedResponse(model.getId(), mv.getId());
    }
}
