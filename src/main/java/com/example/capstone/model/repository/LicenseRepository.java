package com.example.capstone.model.repository;

import com.example.capstone.model.entity.License;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenseRepository extends JpaRepository<License, Long> {
    // 코드(RESEARCH, COMMERCIAL 등)로 조회 가능하도록
    License findByCode(String code);
}
