package com.wallet.service.Transaction;

import com.wallet.Exception.InvalidRequestException;
import com.wallet.Exception.WalletNotFoundException;
import com.wallet.dto.TransactionRequestDTO;
import com.wallet.model.Transaction;
import com.wallet.model.Wallet;
import com.wallet.repository.TransactionRepository;
import com.wallet.repository.WalletRepository;

import java.time.LocalDateTime;

public class Withdraw implements TransactionProcessor {
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    public Withdraw(WalletRepository walletRepository, TransactionRepository transactionRepository) {
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public String process(TransactionRequestDTO request) throws Exception {
        Wallet walletFromRepository = walletRepository.findById(request.getWalletId())
                .orElseThrow(() -> new WalletNotFoundException(request.getWalletId()));

        if (request.getAmount() > walletFromRepository.getBalance()) {
            throw new InvalidRequestException("The amount to be withdrawn exceeds the balance");
        }

        walletFromRepository.setBalance(walletFromRepository.getBalance() - request.getAmount());

        Transaction transaction = new Transaction(
            TransactionType.WITHDRAW.name(),
            request.getAmount(),
            LocalDateTime.now(),
            walletFromRepository
        );

        walletRepository.save(walletFromRepository);

        return transactionRepository.save(transaction).getTransactionId().toString();
    }
}
