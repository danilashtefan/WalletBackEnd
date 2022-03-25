package com.thesis.wallet.controllers;

import com.thesis.wallet.entity.Expanse;
import com.thesis.wallet.entity.ExpanseCategory;
import com.thesis.wallet.entity.Wallet;
import com.thesis.wallet.entity.requests.ExpenseAddOrEditRequest;
import com.thesis.wallet.service.ExpenseCategoriesService;
import com.thesis.wallet.service.ExpenseService;
import com.thesis.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class ExpenseController {
    private final ExpenseService expenseService;
    private final ExpenseCategoriesService expenseCategoriesService;
    private final WalletService walletService;

    @GetMapping("/expanses2")
    public ResponseEntity<List<Expanse>> getAllExpenses(Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        return ResponseEntity.ok().body(expenseService.getAllExpenses(username));
    }

    @DeleteMapping("/expanses2/{id}")
    public ResponseEntity<String> deleteByIdAndUsername(@PathVariable Long id, Authentication authentication){
        String username = (String) authentication.getPrincipal();
        return ResponseEntity.ok().body(expenseService.deleteByIdAndUsername(id, username));

    }

    @GetMapping("/expanses2/{id}")
    public ResponseEntity<Optional<Expanse>> getExpense(@PathVariable Long id, Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        return ResponseEntity.ok().body(expenseService.getExpense(id, username));
    }

    @GetMapping("/expanses2/{id}/category")
    public ResponseEntity<Optional<ExpanseCategory>> getCategoryForExpense(@PathVariable Long id, Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        return ResponseEntity.ok().body(expenseService.getCategoryForExpense(id, username));
    }

    @GetMapping("/expanses2/{id}/wallet")
    public ResponseEntity<Optional<Wallet>> getWalletForExpense(@PathVariable Long id, Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        return ResponseEntity.ok().body(expenseService.getWalletForExpense(id, username));
    }



    @PatchMapping("/expanses2/{id}")
    public ResponseEntity<String> editByIdAndUsername(@PathVariable Long id, @RequestBody ExpenseAddOrEditRequest expenseRequest, Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        Wallet wallet = new Wallet();
        Expanse expense = new Expanse(id, expenseRequest.getName(), expenseRequest.getAmount(), expenseRequest.getPhotoUrl(),
                expenseRequest.getDate(), expenseRequest.getComments(), expenseRequest.getLocation(), expenseRequest.getType(),
                username, expenseCategoriesService.getCategoryById(username,Long.parseLong(expenseRequest.getCategory())), walletService.getWalletById(username, Long.parseLong(expenseRequest.getWallet())));
        return ResponseEntity.ok().body(expenseService.editByIdAndUsername(id, expense, username));
    }

    @PostMapping("/expanses2")
    public ResponseEntity<String> addTransaction(@RequestBody ExpenseAddOrEditRequest expenseRequest, Authentication authentication){
        String username = (String) authentication.getPrincipal();
        Expanse expense = new Expanse(expenseRequest.getName(), expenseRequest.getAmount(), expenseRequest.getPhotoUrl(),
                expenseRequest.getDate(), expenseRequest.getComments(), expenseRequest.getLocation(), expenseRequest.getType(),
                username, expenseCategoriesService.getCategoryById(username,Long.parseLong(expenseRequest.getCategory())), walletService.getWalletById(username, Long.parseLong(expenseRequest.getWallet())));
        return ResponseEntity.ok().body(expenseService.addTransaction(expense));
    }

}
