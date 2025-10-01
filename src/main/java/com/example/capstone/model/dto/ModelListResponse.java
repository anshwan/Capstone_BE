package com.example.capstone.model.dto;

import lombok.*;
import java.time.LocalDate;
import java.util.Map;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ModelListResponse {
    private Long id;
    private String name;
    private String uploader;
    private String versionName;
    private String modality;
    private List<String> license;
    private LocalDate releaseDate;

    /** 요약형 응답에 포함되는 pricing 일부 */
    private Map<String, Object> pricing;

    /** 요약형 응답에 포함되는 metrics 일부 */
    private Map<String, Object> metrics;

    private String thumbnail;
}

