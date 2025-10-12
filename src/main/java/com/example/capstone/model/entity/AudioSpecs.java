package com.example.capstone.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "audio_specs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AudioSpecs {

    /**
     * ✅ Model의 PK(id)를 그대로 공유
     * - @MapsId를 사용하여 model_id가 PK로 자동 복사됨
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
    private Double werKo;
    private Double mos;
    private Double latency;

    /** ✅ 기술 스펙 */
    private String maxAudioInput;
    private String maxAudioOutput;
    private String sampleRate;

    /** ✅ 샘플 데이터 */
    private String sampleInputAudio;
    private String sampleOutput;

    /**
     * ✅ 안전한 생성자 (정적 팩토리 메서드)
     * - model 지정 시 modelId는 자동 세팅됨 (@MapsId 작동)
     */
    public static AudioSpecs of(Model model,
                                Double werKo,
                                Double mos,
                                Double latency,
                                String maxAudioInput,
                                String maxAudioOutput,
                                String sampleRate,
                                String sampleInputAudio,
                                String sampleOutput) {
        AudioSpecs spec = new AudioSpecs();
        spec.setModel(model);
        spec.setWerKo(werKo);
        spec.setMos(mos);
        spec.setLatency(latency);
        spec.setMaxAudioInput(maxAudioInput);
        spec.setMaxAudioOutput(maxAudioOutput);
        spec.setSampleRate(sampleRate);
        spec.setSampleInputAudio(sampleInputAudio);
        spec.setSampleOutput(sampleOutput);
        return spec;
    }
}
