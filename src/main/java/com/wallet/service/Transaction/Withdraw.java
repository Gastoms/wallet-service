package com.wallet.service.Transaction;

import com.wallet.dto.TransactionRequestDTO;

public class Withdraw implements Transaction {

    @Override
    public String process(TransactionRequestDTO request) {

        return "Process withdraw";
    }
}
