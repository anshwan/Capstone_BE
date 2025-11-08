package com.example.capstone.model.repository;

import com.example.capstone.model.entity.LlmSpecs;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.capstone.model.entity.Model;

public interface LlmSpecsRepository extends JpaRepository<LlmSpecs, Long> {
    void deleteByModel(Model model);
}