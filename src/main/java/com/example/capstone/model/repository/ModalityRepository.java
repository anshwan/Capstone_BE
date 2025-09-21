package com.example.capstone.model.repository;

import com.example.capstone.model.entity.Modality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModalityRepository extends JpaRepository<Modality, Long> {
    // 코드(LLM, VLM, IMAGE 등)로 조회 가능하도록
    Modality findByCode(String code);
}

