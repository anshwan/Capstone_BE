package com.example.capstone.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="release_notes")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ReleaseNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="model_id")
    private Model model;

    @Column(columnDefinition = "TEXT")
    private String note;

    @Column(name="created_at")
    private java.time.LocalDateTime createdAt;
}

