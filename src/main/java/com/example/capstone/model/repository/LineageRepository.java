package com.example.capstone.model.repository;

import com.example.capstone.model.entity.Lineage;
import com.example.capstone.model.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LineageRepository extends JpaRepository<Lineage, Long> {

    // ✅ 모델별 현재 최대 step 조회
    @Query("SELECT MAX(l.step) FROM Lineage l WHERE l.model.id = :modelId")
    Integer findMaxStepByModelId(Long modelId);

    // ✅ 특정 모델 이름(toModel)에 대한 부모 관계 1건 조회
    Optional<Lineage> findByToModel(String toModel);

    void deleteAllByModel(Model model);
}
