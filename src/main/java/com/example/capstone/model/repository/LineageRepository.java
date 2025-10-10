package com.example.capstone.model.repository;

import com.example.capstone.model.entity.Lineage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineageRepository extends JpaRepository<Lineage, Long> {}
