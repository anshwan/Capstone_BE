package com.example.capstone.model.repository;

// LicenseRepository.java
import com.example.capstone.model.entity.License;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LicenseRepository extends JpaRepository<License, Long> {
    Optional<License> findByCode(String code);
}

