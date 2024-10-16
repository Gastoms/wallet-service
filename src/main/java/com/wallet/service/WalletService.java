package com.wallet.service;

import com.wallet.Exception.WalletNotFoundException;
import com.wallet.dto.TransactionRequestDTO;
import com.wallet.dto.WalletRequestDTO;
import com.wallet.model.Transaction;
import com.wallet.model.Wallet;
import com.wallet.repository.TransactionRepository;
import com.wallet.repository.WalletRepository;
import com.wallet.service.Transaction.TransactionFactory;
import com.wallet.service.Transaction.TransactionProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public String createWallet(WalletRequestDTO request) {
        Wallet wallet = new Wallet(request.getName(), request.getLastName(), 0);

        return walletRepository.save(wallet).getId().toString();
    }

    public Double getWalletBalance(Long walletId, Optional<LocalDateTime> fromDate) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException(walletId));

        if (fromDate.isPresent()) {
            List<Transaction> transactions = transactionRepository.findByWalletIdAndDateBefore(walletId, fromDate.get());

            return transactions.stream()
                    .mapToDouble(t -> switch (t.getType().toUpperCase()) {
                        case "DEPOSIT", "TRANSFER_IN" -> t.getAmount();
                        case "WITHDRAW", "TRANSFER_OUT" -> -t.getAmount();
                        default -> throw new IllegalStateException("Unexpected transaction type: " + t.getType().toUpperCase());
                    })
                    .sum();
        } else {
            return wallet.getBalance();
        }

    }

    public String processTransaction(TransactionRequestDTO request) throws Exception {
        TransactionFactory transactionFactory = new TransactionFactory(walletRepository, transactionRepository);
        TransactionProcessor transactionProcessor = transactionFactory.createTransaction(request.getType());

        return transactionProcessor.process(request);
    }
}
