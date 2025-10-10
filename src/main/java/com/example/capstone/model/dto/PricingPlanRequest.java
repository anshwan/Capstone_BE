package com.example.capstone.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PricingPlanRequest {
    private String planType;    // research / standard / enterprise
    private String description;
    private String billingType;
    private Integer price;
    private Integer monthlyTokenLimit;
    private Integer monthlyGenerationLimit;
    private Integer monthlyRequestLimit;
    private List<String> rights;
}

