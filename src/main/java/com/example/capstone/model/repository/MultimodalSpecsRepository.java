package com.example.capstone.model.repository;

import com.example.capstone.model.entity.MultimodalSpecs;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.capstone.model.entity.Model;   // ✅ 엔티티 Model


public interface MultimodalSpecsRepository extends JpaRepository<MultimodalSpecs, Long> {
    void deleteByModel(Model model);
}
