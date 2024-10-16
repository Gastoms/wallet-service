package com.wallet.repository;

import com.wallet.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByWalletIdAndDateBefore(Long walletId, LocalDateTime date);
}