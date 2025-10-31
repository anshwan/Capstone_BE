// src/main/java/com/example/capstone/payment/entity/Receipt.java
package com.example.capstone.payment.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "receipts",
        uniqueConstraints = @UniqueConstraint(name = "uq_tx", columnNames = "onchain_tx_hash"),
        indexes = {
                @Index(name = "idx_model", columnList = "model_id"),
                @Index(name = "idx_buyer", columnList = "buyer")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Receipt {

    public enum Status {
        PENDING, VERIFIED, FAILED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model_id", nullable = false)
    private Long modelId;

    @Column(name = "buyer")
    private String buyer;

    @Column(name = "plan", nullable = false, length = 40)
    private String plan; // STANDARD / ENTERPRISE

    @Column(name = "amount_lamports", nullable = false)
    private Long amountLamports;

    @Column(name = "onchain_tx_hash", nullable = false, length = 120)
    private String onchainTxHash;

    @Column(name = "receipt_pda", length = 120)
    private String receiptPda;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private Status status = Status.PENDING;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
