package com.thesis.wallet.controllers;

import com.thesis.wallet.DAO.ExpanseCategoryRepository;
import com.thesis.wallet.entity.Expanse;
import com.thesis.wallet.entity.ExpanseCategory;
import com.thesis.wallet.entity.ExpenseCategoryTotalAmountWrapper;
import com.thesis.wallet.service.ExpenseCategoriesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class ExpenseCategoryController {
    private final ExpenseCategoriesService expenseCategoryService;

    @GetMapping("/expanseCategories2")
    public ResponseEntity<List<ExpanseCategory>> getAllExpenseCategories(Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        return ResponseEntity.ok().body(expenseCategoryService.getAllExpenseCategories(username));
    }

    @GetMapping("/expanseCategories2/expenseCategoriesWithExpenses")
    public ResponseEntity<List<ExpenseCategoryTotalAmountWrapper>> getExpenseCategoriesWithExpenseAmount(Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        return ResponseEntity.ok().body(expenseCategoryService.getExpenseCategoriesWithAmounts(username));
    }

    @GetMapping("/expanseCategories2/topExpenseCategory")
    public ResponseEntity<ExpenseCategoryTotalAmountWrapper> getTopExpenseCategory(Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        return ResponseEntity.ok().body(expenseCategoryService.getTopExpenseCategory(username));
    }

    @GetMapping("/expanseCategories2/topIncomeCategory")
    public ResponseEntity<ExpenseCategoryTotalAmountWrapper> getTopIncomeCategory(Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        return ResponseEntity.ok().body(expenseCategoryService.getTopIncomeCategory(username));
    }
    @DeleteMapping("/expanseCategories2/{id}")
    public ResponseEntity<String> deleteByIdAndUsername(@PathVariable Long id, Authentication authentication){
        String username = (String) authentication.getPrincipal();
        return ResponseEntity.ok().body(expenseCategoryService.deleteByIdAndUsername(id, username));

    }


    @GetMapping("/expanseCategories2/{id}/expenses")
    public ResponseEntity<List<Expanse>> getCategoryFilteredExpenses(Authentication authentication, @PathVariable Long id) {
        String username = (String) authentication.getPrincipal();
        return ResponseEntity.ok().body(expenseCategoryService.getCategoryFilteredExpenses(username ,id));
    }

    @PatchMapping("/expanseCategories2/{id}")
    public ResponseEntity<String> editByIdAndUsername(@PathVariable Long id, @RequestBody ExpanseCategory category, Authentication authentication){
        String username = (String) authentication.getPrincipal();
        return ResponseEntity.ok().body(expenseCategoryService.editByIdAndUsername(id, category, username));
    }


}
