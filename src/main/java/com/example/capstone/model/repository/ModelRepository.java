package com.example.capstone.model.repository;

import com.example.capstone.model.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ModelRepository extends JpaRepository<Model, Long> {

    @Query("SELECT DISTINCT m FROM Model m " +
            "LEFT JOIN FETCH m.pricingPlans " +
            "LEFT JOIN FETCH m.lineage " +
            "LEFT JOIN FETCH m.releaseNotes " +
            "WHERE m.id = :id")
    Optional<Model> findDetailById(@Param("id") Long id);

}
