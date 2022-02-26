package com.thesis.wallet.controllers;


import com.thesis.wallet.entity.Expanse;
import com.thesis.wallet.entity.ExpanseCategory;
import com.thesis.wallet.entity.Wallet;
import com.thesis.wallet.service.ExpenseService;
import com.thesis.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("wallets2/{id}")
    public ResponseEntity<String> editByIdAndUsername(@PathVariable Long id, @RequestBody Wallet wallet, Authentication authentication){
        String username = (String) authentication.getPrincipal();
        return ResponseEntity.ok().body(walletService.editByIdAndUsername(id, wallet, username));

    }
}
