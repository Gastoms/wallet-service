package com.wallet.service;

import com.wallet.dto.TransactionRequestDTO;
import com.wallet.dto.WalletRequestDTO;
import com.wallet.model.Wallet;
import com.wallet.repository.TransactionRepository;
import com.wallet.repository.UserRepository;
import com.wallet.service.Transaction.Transaction;
import com.wallet.service.Transaction.TransactionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public String createWallet(WalletRequestDTO request) {
        Wallet wallet = new Wallet();
        wallet.setName(request.getName());
        wallet.setLastName(request.getLastName());
        wallet.setBalance(0);

        return userRepository.save(wallet).getId().toString();
    }

    public String processTrasaction(TransactionRequestDTO request) {
        Transaction transaction = TransactionFactory.createTransaction(request.getType());

        return transaction.process(request);
    }
}
