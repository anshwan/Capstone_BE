package com.example.capstone.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "licenses")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LicenseDef {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true, length=50)
    private String code;

    @Column(nullable=false, length=100)
    private String name;

    @Column(columnDefinition = "text")
    private String description;
}
