package com.wallet.service.Transaction;

public class TransactionFactory {

    public static Transaction createTransaction(String type) {
        TransactionType transactionType;
        try {
            transactionType = TransactionType.valueOf(type.toUpperCase());
        } catch (Exception e) {
            throw new RuntimeException("trasaccion invalida");
        }

        return switch (transactionType) {
            case DEPOSIT -> new Deposit();
            case WITHDRAW -> new Withdraw();
            default -> null;
        };
    }
}
