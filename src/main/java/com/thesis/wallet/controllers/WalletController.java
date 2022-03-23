package com.thesis.wallet.controllers;


import com.thesis.wallet.entity.Expanse;
import com.thesis.wallet.entity.ExpenseCategoryTotalAmountWrapper;
import com.thesis.wallet.entity.Wallet;
import com.thesis.wallet.entity.WalletTotalAmountWrapper;
import com.thesis.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
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

    @GetMapping("/wallets2/{id}/expenses")
    public ResponseEntity<List<Expanse>> getWalletFilteredExpenses(Authentication authentication, @PathVariable Long id) {
        String username = (String) authentication.getPrincipal();
        return ResponseEntity.ok().body(walletService.getWalletFilteredExpenses(username, id));
    }

    @GetMapping("/wallets2/topExpenseWallet/{minDate}/{maxDate}")
    public ResponseEntity<WalletTotalAmountWrapper> getTopExpenseWallet(Authentication authentication, @PathVariable String minDate, @PathVariable String maxDate) throws ParseException {
        String username = (String) authentication.getPrincipal();
        return ResponseEntity.ok().body(walletService.getTopExpenseWallet(username, minDate, maxDate));
    }

    @GetMapping("/wallets2/topIncomeWallet/{minDate}/{maxDate}")
    public ResponseEntity<WalletTotalAmountWrapper> getTopIncomeWallet(Authentication authentication, @PathVariable String minDate, @PathVariable String maxDate) throws ParseException {
        String username = (String) authentication.getPrincipal();
        return ResponseEntity.ok().body(walletService.getTopIncomeWallet(username, minDate, maxDate));
    }

    @GetMapping("/wallets2/walletsWithExpenses/{minDate}/{maxDate}")
    public ResponseEntity<List<WalletTotalAmountWrapper>> getWalletsWithExpenseAmount(Authentication authentication, @PathVariable String minDate, @PathVariable String maxDate) throws ParseException {
        String username = (String) authentication.getPrincipal();
        return ResponseEntity.ok().body(walletService.getWalletsWithAmounts(username, minDate, maxDate));
    }

    @DeleteMapping("wallets2/{id}")
    public ResponseEntity<String> deleteByIdAndUsername(@PathVariable Long id, Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        return ResponseEntity.ok().body(walletService.deleteByIdAndUsername(id, username));

    }

    @PatchMapping("wallets2/{id}")
    public ResponseEntity<String> editByIdAndUsername(@PathVariable Long id, @RequestBody Wallet wallet, Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        return ResponseEntity.ok().body(walletService.editByIdAndUsername(id, wallet, username));
    }
}
