package com.example.capstone.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="pricing_plans")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PricingPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="model_id")
    private Model model;

    @Enumerated(EnumType.STRING)
    private PlanType planType;    // research / standard / enterprise

    private Double price;
    private String description;

    @Enumerated(EnumType.STRING)
    private BillingType billingType;

    private Integer monthlyTokenLimit;
    private Integer monthlyGenerationLimit;
    private Integer monthlyRequestLimit;

    @Column(columnDefinition = "json")
    private String rights;  // ["상업적", "API 액세스", ...]
}

