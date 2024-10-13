package com.wallet.controller;

import com.wallet.dto.WalletRequestDTO;
import com.wallet.dto.TransactionRequestDTO;
import com.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PostMapping
    public String createWallet(@RequestBody WalletRequestDTO walletRequestDTO) {
        return walletService.createWallet(walletRequestDTO);
    }

    @PostMapping("/transaction")
    public String processTransaction(@RequestBody TransactionRequestDTO request) {
        return walletService.processTrasaction(request);
    }

}
