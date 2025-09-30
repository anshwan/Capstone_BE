package com.example.capstone.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "models")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 모델 이름 */
    @Column(nullable = false, length = 200)
    private String name;

    /** 업로더/회사명 */
    @Column(nullable = false, length = 200)
    private String uploader;

    /** 썸네일 이미지 */
    @Column(length = 500)
    private String thumbnail;

    /** 컴플라이언스 정보 */
    @Column(length = 200)
    private String compliance;

    /** 등록자 (AppUser FK) */
    @Column(name = "created_by")
    private Long createdBy;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
