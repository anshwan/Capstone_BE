package com.example.capstone.model.repository;

import com.example.capstone.model.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Long> {
    boolean existsByNameAndCreatedBy_Id(String name, Long createdById);
}

