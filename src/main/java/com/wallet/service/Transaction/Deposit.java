package com.wallet.service.Transaction;

import com.wallet.dto.TransactionRequestDTO;

public class Deposit implements Transaction {

    @Override
    public String process(TransactionRequestDTO request) {

        return "process deposit";
    }
}
