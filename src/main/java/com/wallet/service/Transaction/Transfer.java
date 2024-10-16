package com.wallet.service.Transaction;

import com.wallet.Exception.InsufficientFundsException;
import com.wallet.Exception.InvalidRequestException;
import com.wallet.Exception.WalletNotFoundException;
import com.wallet.dto.TransactionRequestDTO;
import com.wallet.model.Transaction;
import com.wallet.model.Wallet;
import com.wallet.repository.TransactionRepository;
import com.wallet.repository.WalletRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

public class Transfer implements TransactionProcessor {

    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    public Transfer(WalletRepository walletRepository, TransactionRepository transactionRepository) {
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    @Transactional
    public String process(TransactionRequestDTO request) {

        Wallet walletOrigin = walletRepository.findById(request.getWalletId())
                    .orElseThrow(() -> new WalletNotFoundException("Origin wallet ID not found: " + request.getWalletId()));

        if (request.getDestinationWalletId() != null) {
            Wallet walletDestination = walletRepository.findById(request.getDestinationWalletId())
                    .orElseThrow(() -> new WalletNotFoundException("Destination wallet ID not found: " + request.getDestinationWalletId()));

            if (walletOrigin.getBalance() < request.getAmount()) {
                throw new InsufficientFundsException("Insufficient balance in the origin wallet");
            }

            walletOrigin.setBalance(walletOrigin.getBalance() - request.getAmount());
            walletRepository.save(walletOrigin);

            walletDestination.setBalance(walletDestination.getBalance() + request.getAmount());
            walletRepository.save(walletDestination);

            Transaction transactionOut = new Transaction(
                TransferType.TRANSFER_OUT.name(),
                request.getAmount(),
                LocalDateTime.now(),
                walletOrigin
            );

            Transaction transactionIn = new Transaction(
                TransferType.TRANSFER_IN.name(),
                request.getAmount(),
                LocalDateTime.now(),
                walletDestination
            );

            transactionRepository.save(transactionIn);

            return transactionRepository.save(transactionOut).getTransactionId().toString();

        } else {
            throw new InvalidRequestException("destination_wallet_id field is mandatory");
        }

    }
}
