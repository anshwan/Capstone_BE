package com.example.capstone.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="llm_specs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LlmSpecs {
    @Id
    private Long modelId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name="model_id")
    private Model model;

    private Double mmlu;
    private Double hellaswag;
    private Double arc;
    private Double truthfulqa;
    private Double gsm8k;
    private Double humaneval;

    private String contextWindow;
    private Integer maxOutputTokens;

    @Column(columnDefinition = "TEXT")
    private String sampleOutput;
}
