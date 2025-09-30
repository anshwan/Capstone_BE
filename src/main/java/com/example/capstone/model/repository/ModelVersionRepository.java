package com.example.capstone.model.repository;

import com.example.capstone.model.entity.ModelVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ModelVersionRepository extends JpaRepository<ModelVersion, Long> {

    // 기존 버전 조회
    List<ModelVersion> findByModelIdOrderByIdDesc(Long modelId);

    // fetch join으로 modality, license를 같이 로딩
    @Query("""
        SELECT v FROM ModelVersion v
        JOIN FETCH v.modality m
        WHERE v.model.id = :modelId
        ORDER BY v.id DESC
    """)
    List<ModelVersion> findByModelIdWithRelations(@Param("modelId") Long modelId);
}
