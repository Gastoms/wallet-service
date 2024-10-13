package com.wallet.repository;

import com.wallet.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Wallet, Long> {
}
