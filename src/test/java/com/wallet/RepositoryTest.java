package com.wallet;

import com.wallet.model.Transaction;
import com.wallet.model.Wallet;
import com.wallet.repository.TransactionRepository;
import com.wallet.repository.WalletRepository;
import com.wallet.service.Transaction.TransactionType;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@DataJpaTest
public class RepositoryTest {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    public void testSaveWallet() {
        Wallet wallet = new Wallet("Pepe", "Diaz", 0);

        walletRepository.save(wallet);

        Optional<Wallet> walletFromRepository = walletRepository.findById(wallet.getId());

        assertTrue(walletFromRepository.isPresent());
        assertEquals("Pepe", walletFromRepository.get().getName());
        assertEquals(Double.valueOf(0), walletFromRepository.get().getBalance());
    }

    @Test
    public void testSaveTransaction() {
        Wallet wallet = new Wallet("Pepe", "Diaz", 0);
        walletRepository.save(wallet);

        Transaction transaction = new Transaction(TransactionType.DEPOSIT.name(), 2.5, LocalDateTime.now(), wallet);
        transactionRepository.save(transaction);

        Optional<Transaction> transactionFromRepository = transactionRepository.findById(transaction.getTransactionId());

        assertTrue(transactionFromRepository.isPresent());
        assertEquals(TransactionType.DEPOSIT.name(), transactionFromRepository.get().getType());
        assertEquals(2.5, transactionFromRepository.get().getAmount());
    }
}
