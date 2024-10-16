package com.wallet.service.Transaction;

import com.wallet.Exception.InvalidTransactionType;
import com.wallet.repository.TransactionRepository;
import com.wallet.repository.WalletRepository;

public class TransactionFactory {

    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;

    public TransactionFactory(WalletRepository walletRepository, TransactionRepository transactionRepository) {
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    public TransactionProcessor createTransaction(String type) {
        TransactionType transactionType;
        try {
            transactionType = TransactionType.valueOf(type.toUpperCase());
        } catch (Exception e) {
            throw new InvalidTransactionType(type);
        }

        return switch (transactionType) {
            case DEPOSIT -> new Deposit(walletRepository, transactionRepository);
            case WITHDRAW -> new Withdraw(walletRepository, transactionRepository);
            case TRANSFER -> new Transfer(walletRepository, transactionRepository);
        };
    }
}
