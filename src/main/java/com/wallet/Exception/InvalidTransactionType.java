package com.wallet.Exception;

public class InvalidTransactionType extends RuntimeException {
    public InvalidTransactionType(String type) {
        super("The transaction type is invalid: " + type);
    }
}
