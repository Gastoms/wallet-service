package com.wallet.dto;

public class TransferRequestDTO {
    private Long originWalletId;
    private Long destinationWalletId;
    private Double amount;

    public Long getOriginWalletId() {
        return originWalletId;
    }

    public void setOriginWalletId(Long originWalletId) {
        this.originWalletId = originWalletId;
    }

    public Long getDestinationWalletId() {
        return destinationWalletId;
    }

    public void setDestinationWalletId(Long destinationWalletId) {
        this.destinationWalletId = destinationWalletId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
