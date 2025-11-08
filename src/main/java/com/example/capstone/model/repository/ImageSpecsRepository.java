package com.example.capstone.model.repository;

import com.example.capstone.model.entity.ImageSpecs;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.capstone.model.entity.Model;

public interface ImageSpecsRepository extends JpaRepository<ImageSpecs, Long> {
    void deleteByModel(Model model);
}