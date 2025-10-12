package com.example.capstone.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "llm_specs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LlmSpecs {

    /**
     * ✅ Model의 PK(id)를 그대로 공유
     * - @MapsId로 인해 model의 id가 이 엔티티의 PK로 복사됨
     * - 절대 수동으로 setModelId()를 호출하거나 builder에 넣지 말 것
     */
    @Id
    @Column(name = "model_id")
    private Long modelId;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(name = "model_id")
    private Model model;

    /** ✅ LLM 관련 성능 지표 */
    private Double mmlu;
    private Double hellaswag;
    private Double arc;
    private Double truthfulqa;
    private Double gsm8k;
    private Double humaneval;

    /** ✅ 기술 스펙 */
    private String contextWindow;
    private Integer maxOutputTokens;

    /** ✅ 샘플 출력 */
    @Column(columnDefinition = "TEXT")
    private String sampleOutput;

    /**
     * ✅ 편의 생성자
     * model 지정 시 modelId 자동 세팅됨 (@MapsId 덕분)
     */
    public static LlmSpecs of(Model model,
                              Double mmlu, Double hellaswag, Double arc,
                              Double truthfulqa, Double gsm8k, Double humaneval,
                              String contextWindow, Integer maxOutputTokens,
                              String sampleOutput) {
        LlmSpecs spec = new LlmSpecs();
        spec.setModel(model);
        spec.setMmlu(mmlu);
        spec.setHellaswag(hellaswag);
        spec.setArc(arc);
        spec.setTruthfulqa(truthfulqa);
        spec.setGsm8k(gsm8k);
        spec.setHumaneval(humaneval);
        spec.setContextWindow(contextWindow);
        spec.setMaxOutputTokens(maxOutputTokens);
        spec.setSampleOutput(sampleOutput);
        return spec;
    }
}
