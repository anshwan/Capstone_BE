package com.example.capstone.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "image_specs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageSpecs {

    /**
     * ✅ Model의 PK(id)를 그대로 공유하는 1:1 관계
     * - @MapsId로 인해 model_id가 이 엔티티의 PK로 자동 복사됨
     * - 절대 수동으로 setModelId() 호출하거나 builder에 넣지 말 것
     */
    @Id
    @Column(name = "model_id")
    private Long modelId;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(name = "model_id")
    private Model model;

    /** ✅ 이미지 생성 모델 관련 평가 지표 */
    private Double fid;
    private Double inceptionScore;
    private Double clipScore;

    /** ✅ 기술 스펙 */
    private Integer promptTokens;
    private String maxOutputResolution;

    /** ✅ 샘플 데이터 */
    private String samplePrompt;
    private String sampleOutputImage;

    /**
     * ✅ 안전한 생성자 (정적 팩토리 메서드)
     * - model을 지정하면 modelId는 자동 세팅됨 (@MapsId)
     */
    public static ImageSpecs of(Model model,
                                Double fid,
                                Double inceptionScore,
                                Double clipScore,
                                Integer promptTokens,
                                String maxOutputResolution,
                                String samplePrompt,
                                String sampleOutputImage) {
        ImageSpecs spec = new ImageSpecs();
        spec.setModel(model);
        spec.setFid(fid);
        spec.setInceptionScore(inceptionScore);
        spec.setClipScore(clipScore);
        spec.setPromptTokens(promptTokens);
        spec.setMaxOutputResolution(maxOutputResolution);
        spec.setSamplePrompt(samplePrompt);
        spec.setSampleOutputImage(sampleOutputImage);
        return spec;
    }
}
