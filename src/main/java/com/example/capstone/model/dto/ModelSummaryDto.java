package com.example.capstone.model.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelSummaryDto {
    private Long id;
    private String name;
    private String uploader;
    private String modality;
    private String license;
    private String currency;
    private Double priceStandard;
    private Map<String, Object> metrics; // ✅ String → Map 으로 변경
}
