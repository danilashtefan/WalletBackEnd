package com.thesis.wallet.controllers;


import com.thesis.wallet.entity.*;
import com.thesis.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

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
    public ResponseEntity<Wallet> getWallet(@PathVariable Long id, Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        return ResponseEntity.ok().body(walletService.getWalletById(username, id));
    }

    @GetMapping("/wallets2/{id}/expenses")
    public ResponseEntity<List<Expense>> getWalletFilteredExpenses(Authentication authentication, @PathVariable Long id) {
        String username = (String) authentication.getPrincipal();
        return ResponseEntity.ok().body(walletService.getWalletFilteredExpenses(username, id));
    }

    @GetMapping("/wallets2/topExpenseWallet")
    public ResponseEntity<WalletTotalAmountWrapper> getTopExpenseWallet(Authentication authentication, @RequestParam("minDate") String minDate, @RequestParam("maxDate") String maxDate) throws ParseException {
        String username = (String) authentication.getPrincipal();
        return ResponseEntity.ok().body(walletService.getTopExpenseWallet(username, minDate, maxDate));
    }

    @GetMapping("/wallets2/topIncomeWallet")
    public ResponseEntity<WalletTotalAmountWrapper> getTopIncomeWallet(Authentication authentication, @RequestParam("minDate") String minDate, @RequestParam("maxDate") String maxDate) throws ParseException {
        String username = (String) authentication.getPrincipal();
        return ResponseEntity.ok().body(walletService.getTopIncomeWallet(username, minDate, maxDate));
    }

    @GetMapping("/wallets2/walletsWithExpenses")
    public ResponseEntity<List<WalletTotalAmountWrapper>> getWalletsWithExpenseAmount(Authentication authentication, @RequestParam("minDate") String minDate, @RequestParam("maxDate") String maxDate) throws ParseException {
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

    @PostMapping("/wallets2")
    public ResponseEntity<String> addCWallet(@RequestBody Wallet wallet, Authentication authentication){
        String username = (String) authentication.getPrincipal();
        wallet.setUsername(username);
        return ResponseEntity.ok().body(walletService.addWallet(wallet));
    }
}
