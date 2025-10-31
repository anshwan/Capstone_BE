package com.example.capstone.payment.repository;

import com.example.capstone.payment.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    Optional<Receipt> findByOnchainTxHash(String txHash);

    // ✅ buyer_wallet 별 전체 영수증 조회
    List<Receipt> findAllByBuyer(String buyer);
}
