package com.wallet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionRequestDTO {
    @JsonProperty("wallet_id")
    private Long walletId;
    private String type;
    private double amount;
    @JsonProperty("destination_wallet_id")
    private Long destinationWalletId;

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getDestinationWalletId() {
        return destinationWalletId;
    }

    public void setDestinationWalletId(Long destinationWalletId) {
        this.destinationWalletId = destinationWalletId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
