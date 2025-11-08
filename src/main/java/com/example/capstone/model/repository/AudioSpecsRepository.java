package com.example.capstone.model.repository;

import com.example.capstone.model.entity.AudioSpecs;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.capstone.model.entity.Model;

public interface AudioSpecsRepository extends JpaRepository<AudioSpecs, Long> {
    void deleteByModel(Model model);
}
