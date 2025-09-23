package com.example.capstone.model.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
public class ModelUploadRequest {
    private String modelName;
    private String versionName;
    private String modalityCode;
    private String licenseCode;
    private Double priceResearch;
    private String currency;
    private Double priceStandard;
    private Double priceEnterprise;
    private String overview;
    private String releaseNotes;
    private LocalDate releaseDate;
    private Map<String,Object> storage;
    private List<Object> metrics;
    private List<Object> samples;
    private List<Object> lineage;
    private String cidRoot;
    private String checksumRoot;
    private String walletAddress;
    private String signature;
}
