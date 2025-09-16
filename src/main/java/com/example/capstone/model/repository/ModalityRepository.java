package com.example.capstone.model.repository;

import com.example.capstone.model.entity.Modality;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ModalityRepository extends JpaRepository<Modality, Long> {
    Optional<Modality> findByCode(String code);
}

