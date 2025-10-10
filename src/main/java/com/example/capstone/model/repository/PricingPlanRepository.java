package com.example.capstone.model.repository;

import com.example.capstone.model.entity.PricingPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricingPlanRepository extends JpaRepository<PricingPlan, Long> {}
