package com.example.capstone.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="audio_specs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AudioSpecs {
    @Id
    private Long modelId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name="model_id")
    private Model model;

    private Double werKo;
    private Double mos;
    private Double latency;

    private String maxAudioInput;
    private String maxAudioOutput;
    private String sampleRate;

    private String sampleInputAudio;
    private String sampleOutput;
}
