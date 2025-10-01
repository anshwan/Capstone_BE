package com.example.capstone.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="image_specs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageSpecs {
    @Id
    private Long modelId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name="model_id")
    private Model model;

    private Double fid;
    private Double inceptionScore;
    private Double clipScore;

    private Integer promptTokens;
    private String maxOutputResolution;

    private String samplePrompt;
    private String sampleOutputImage;
}
