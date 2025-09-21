package com.example.capstone.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUser {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "google_sub", unique = true, nullable = false, length = 128)
    private String googleSub;

    @Column(unique = true, nullable = false, length = 320)
    private String email;

    @Column(length = 120)
    private String name;

    @Column(name = "picture_url", columnDefinition = "text")
    private String pictureUrl;

    @Builder.Default
    @Column(name = "created_at", nullable = false)
    private Instant createdAt = Instant.now();

    @Column(name = "wallet_address", length = 64)
    private String walletAddress;

    @PrePersist
    void onCreate() {
        if (createdAt == null) createdAt = Instant.now();
    }

    /** 구글 프로필 동기화용 */
    public void updateFromGoogle(String email, String name, String pictureUrl) {
        if (email != null) this.email = email;
        if (name != null) this.name = name;
        if (pictureUrl != null) this.pictureUrl = pictureUrl;
    }

    /** 지갑 주소 업데이트 */
    public void updateWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }
}
