package com.example.capstone.model.repository;

import com.example.capstone.model.entity.Model;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ModelRepository extends JpaRepository<Model, Long> {

    /** 상세 조회 → pricingPlans만 fetch join */
    @EntityGraph(attributePaths = {"pricingPlans"})
    @Query("select m from Model m where m.id = :id")
    Optional<Model> findDetailById(@Param("id") Long id);
}
