package com.wallet.service.Transaction;

import com.wallet.dto.TransactionRequestDTO;

public interface Transaction {
    String process(TransactionRequestDTO request);
}
