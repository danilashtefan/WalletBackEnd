package com.thesis.wallet.controllers;


import com.thesis.wallet.entity.Expanse;
import com.thesis.wallet.entity.Wallet;
import com.thesis.wallet.service.ExpenseService;
import com.thesis.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class WalletController {

    private final WalletService walletService;

    @GetMapping("/wallets2")
    public ResponseEntity<List<Wallet>> getAllWallets(Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        return ResponseEntity.ok().body(walletService.getAllWallets(username));
    }

    @GetMapping("/wallets2/{id}")
    public ResponseEntity<Optional<Wallet>> getWallet(@PathVariable Long id) {
        return ResponseEntity.ok().body(walletService.getWallet(id));
    }
}
