package com.wallet.Exception;

public class WalletNotFoundException extends RuntimeException {
    public WalletNotFoundException(Long walletId) {
        super("Wallet ID not found: " + walletId.toString());
    }

    public WalletNotFoundException(String message) {
        super(message);
    }
}
