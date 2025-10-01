package com.example.capstone.model.repository;

import com.example.capstone.model.entity.Model;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ModelRepository extends JpaRepository<Model, Long> {

    // 🔹 전체 조회 시 pricingPlans까지 한 번에 로딩
    @EntityGraph(attributePaths = {"pricingPlans"})
    @Query("select m from Model m")
    List<Model> findAllWithPricing();

    // 🔹 상세 조회 시 필요한 것들까지 한 번에 로딩
    @EntityGraph(attributePaths = {"pricingPlans", "lineage", "releaseNotes"})
    @Query("select m from Model m where m.id = :id")
    Optional<Model> findDetailById(@Param("id") Long id);
}
