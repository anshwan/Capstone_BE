package com.example.capstone.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "modality")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Modality {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true, length=32)
    private String code; // LLM, VLM, IMAGE

    @Column(nullable=false, length=64)
    private String name;
}
