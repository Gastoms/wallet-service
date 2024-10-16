package com.wallet.service.Transaction;

import com.wallet.dto.TransactionRequestDTO;

public interface TransactionProcessor {
    String process(TransactionRequestDTO request) throws Exception;
}