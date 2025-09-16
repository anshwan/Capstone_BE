package com.example.capstone.model.repository;

import com.example.capstone.model.entity.LicenseDef;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LicenseDefRepository extends JpaRepository<LicenseDef, Long> {
    Optional<LicenseDef> findByCode(String code);
}
