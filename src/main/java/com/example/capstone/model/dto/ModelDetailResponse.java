package com.example.capstone.model.dto;

import lombok.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ModelDetailResponse {
    private Long id;
    private String name;
    private String uploader;
    private String versionName;
    private String modality;
    private List<String> license;
    private LocalDate releaseDate;
    private String overview;
    private Map<String, Object> pricing;
    private Map<String, Object> metrics;
    private Map<String, Object> technicalSpecs;
    private String compliance;
    private Object sample;
    private List<Map<String,Object>> lineage;
    private List<String> releaseNotes;
    private String cidRoot;
    private String checksumRoot;
    private String onchainTx;
    private String thumbnail;
}
