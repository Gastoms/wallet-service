package com.wallet.controller;

import com.wallet.dto.TransactionRequestDTO;
import com.wallet.dto.WalletRequestDTO;
import com.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PostMapping
    public ResponseEntity<String> createWallet(@RequestBody WalletRequestDTO walletRequestDTO) {
        String id = walletService.createWallet(walletRequestDTO);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("/{walletId}/balance")
    public ResponseEntity<Double> getBalance(@PathVariable Long walletId,
                                             @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate) {

        Optional<LocalDateTime> date = Optional.ofNullable(fromDate);
        Double balance = walletService.getWalletBalance(walletId, date);

        return new ResponseEntity<>(balance, HttpStatus.OK);
    }

    @PostMapping("/transaction")
    public ResponseEntity<String> processTransaction(@RequestBody TransactionRequestDTO request) throws Exception {
        String id = walletService.processTransaction(request);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
