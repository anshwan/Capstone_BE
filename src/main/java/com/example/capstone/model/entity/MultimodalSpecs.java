package com.example.capstone.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="multimodal_specs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MultimodalSpecs {
    @Id
    private Long modelId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name="model_id")
    private Model model;

    private Double mme;
    private Double ocrF1;
    private Double vqav2;

    private String textTokens;
    private Integer maxImages;
    private String maxImageResolution;

    private String sampleInputImage;
    private String samplePrompt;
    private String sampleOutput;
}

