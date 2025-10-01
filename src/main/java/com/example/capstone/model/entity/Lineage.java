package com.example.capstone.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="lineage")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Lineage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="model_id")
    private Model model;

    private Integer step;
    private String fromModel;
    private String toModel;

    @Enumerated(EnumType.STRING)
    private Relationship relationship;
}
