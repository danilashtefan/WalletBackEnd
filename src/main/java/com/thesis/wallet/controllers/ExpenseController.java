package com.thesis.wallet.controllers;

import com.thesis.wallet.entity.Category;
import com.thesis.wallet.entity.Expense;
import com.thesis.wallet.entity.Wallet;
import com.thesis.wallet.entity.requests.ExpenseAddOrEditRequest;
import com.thesis.wallet.service.ExpenseCategoriesService;
import com.thesis.wallet.service.ExpenseService;
import com.thesis.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ExpenseController {

    @Autowired
    private final ExpenseService expenseService;
    @Autowired
    private final ExpenseCategoriesService expenseCategoriesService;

    @Autowired
    private final WalletService walletService;

    @GetMapping("/expanses2")
    public ResponseEntity<List<Expense>> getAllExpenses(Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        return ResponseEntity.ok().body(expenseService.getAllExpenses(username));
    }

    @DeleteMapping("/expanses2/{id}")
    public ResponseEntity<String> deleteByIdAndUsername(@PathVariable Long id, Authentication authentication){
        String username = (String) authentication.getPrincipal();
        return ResponseEntity.ok().body(expenseService.deleteByIdAndUsername(id, username));
    }

    @GetMapping("/expanses2/{id}")
    public ResponseEntity<Optional<Expense>> getExpense(@PathVariable Long id, Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        return ResponseEntity.ok().body(expenseService.getExpense(id, username));
    }

    @GetMapping("/expanses2/{id}/category")
    public ResponseEntity<Optional<Category>> getCategoryForExpense(@PathVariable Long id, Authentication authentication) {
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
        Expense expense = new Expense(id, expenseRequest.getName(), expenseRequest.getAmount(),
                expenseRequest.getDate(), expenseRequest.getComments(), expenseRequest.getLocation(), expenseRequest.getType(),
                username, expenseCategoriesService.getCategoryById(username,Long.parseLong(expenseRequest.getCategory())), walletService.getWalletById(username, Long.parseLong(expenseRequest.getWallet())));
        return ResponseEntity.ok().body(expenseService.editByIdAndUsername(id, expense, username));
    }

    @PostMapping("/expanses2")
    public ResponseEntity<String> addTransaction(@RequestBody ExpenseAddOrEditRequest expenseRequest, Authentication authentication){
        String username = (String) authentication.getPrincipal();
        Expense expense = new Expense(expenseRequest.getName(), expenseRequest.getAmount(),
                expenseRequest.getDate(), expenseRequest.getComments(), expenseRequest.getLocation(), expenseRequest.getType(),
                username, expenseCategoriesService.getCategoryById(username,Long.parseLong(expenseRequest.getCategory())), walletService.getWalletById(username, Long.parseLong(expenseRequest.getWallet())));
        return ResponseEntity.ok().body(expenseService.addTransaction(expense));
    }

    @GetMapping("/test")
    public void test(){
        System.out.println("Real test hit");
    }

    @GetMapping("/expanses2/topExpenseTransaction")
    public ResponseEntity<Expense> getTopExpenseTransaction(Authentication authentication, @RequestParam("minDate") String minDate, @RequestParam("maxDate") String maxDate) throws ParseException {
        String username = (String) authentication.getPrincipal();
        return ResponseEntity.ok().body(expenseService.getTopTransaction(username, minDate, maxDate, "Expense"));
    }

    @GetMapping("/expanses2/topIncomeTransaction")
    public ResponseEntity<Expense> getTopIncomeTransaction(Authentication authentication, @RequestParam("minDate") String minDate, @RequestParam("maxDate") String maxDate) throws ParseException {
        String username = (String) authentication.getPrincipal();
        return ResponseEntity.ok().body(expenseService.getTopTransaction(username, minDate, maxDate, "Income"));
    }

}
