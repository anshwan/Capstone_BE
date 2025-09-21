package com.example.capstone.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "licenses")
@Getter
@Setter
public class License {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 32, unique = true)
    private String code;   // RESEARCH, COMMERCIAL, ONPREM, FT

    @Column(nullable = false, length = 50)
    private String name;   // 연구용, 상업용 등

    @Column(length = 255)
    private String description; // 상세 설명
}
