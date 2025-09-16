package com.example.capstone.model.entity;

import com.example.capstone.user.entity.AppUser;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "models")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Model {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=200)
    private String name;

    @ManyToOne(optional = false) @JoinColumn(name = "created_by")
    private AppUser createdBy;

    @Column(name="created_at", nullable=false, updatable=false, insertable=false,
            columnDefinition = "timestamp default current_timestamp")
    private Instant createdAt;

    @Column(name="updated_at", nullable=false, insertable=false,
            columnDefinition = "timestamp default current_timestamp on update current_timestamp")
    private Instant updatedAt;
}
