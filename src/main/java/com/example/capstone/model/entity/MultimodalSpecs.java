package com.example.capstone.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "multimodal_specs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MultimodalSpecs {

    /**
     * ✅ Model의 PK(id)를 그대로 공유
     * - @MapsId 덕분에 model_id가 PK로 자동 복사됨
     * - 절대 수동으로 setModelId() 호출하거나 builder에 넣지 말 것
     */
    @Id
    @Column(name = "model_id")
    private Long modelId;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(name = "model_id")
    private Model model;

    /** ✅ 성능 지표 */
    private Double mme;
    private Double ocrF1;
    private Double vqav2;

    /** ✅ 기술 스펙 */
    private String textTokens;
    private Integer maxImages;
    private String maxImageResolution;

    /** ✅ 샘플 데이터 */
    private String sampleInputImage;
    private String samplePrompt;
    private String sampleOutput;

    /**
     * ✅ 안전한 정적 팩토리 메서드
     * - model 지정 시 modelId는 자동 세팅됨 (@MapsId 작동)
     */
    public static MultimodalSpecs of(Model model,
                                     Double mme,
                                     Double ocrF1,
                                     Double vqav2,
                                     String textTokens,
                                     Integer maxImages,
                                     String maxImageResolution,
                                     String sampleInputImage,
                                     String samplePrompt,
                                     String sampleOutput) {
        MultimodalSpecs spec = new MultimodalSpecs();
        spec.setModel(model);
        spec.setMme(mme);
        spec.setOcrF1(ocrF1);
        spec.setVqav2(vqav2);
        spec.setTextTokens(textTokens);
        spec.setMaxImages(maxImages);
        spec.setMaxImageResolution(maxImageResolution);
        spec.setSampleInputImage(sampleInputImage);
        spec.setSamplePrompt(samplePrompt);
        spec.setSampleOutput(sampleOutput);
        return spec;
    }
}
