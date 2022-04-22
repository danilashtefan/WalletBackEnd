package com.thesis.wallet.controllers;

import com.thesis.wallet.entity.Expense;
import com.thesis.wallet.entity.Category;
import com.thesis.wallet.entity.ExpenseCategoryTotalAmountWrapper;
import com.thesis.wallet.service.ExpenseCategoriesService;
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
public class ExpenseCategoryController {
    private final ExpenseCategoriesService expenseCategoryService;

    @GetMapping("/expanseCategories2")
    public ResponseEntity<List<Category>> getAllExpenseCategories(Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        return ResponseEntity.ok().body(expenseCategoryService.getAllExpenseCategories(username));
    }

    @GetMapping("/expanseCategories2/expenseCategoriesWithExpenses")
    public ResponseEntity<List<ExpenseCategoryTotalAmountWrapper>> getExpenseCategoriesWithExpenseAmount(Authentication authentication, @RequestParam("minDate") String minDate, @RequestParam("maxDate") String maxDate) throws ParseException {
        String username = (String) authentication.getPrincipal();
        return ResponseEntity.ok().body(expenseCategoryService.getExpenseCategoriesWithAmounts(username, minDate, maxDate));
    }

    @GetMapping("/expanseCategories2/topExpenseCategory")
    public ResponseEntity<ExpenseCategoryTotalAmountWrapper> getTopExpenseCategory(Authentication authentication, @RequestParam("minDate") String minDate, @RequestParam("maxDate") String maxDate) throws ParseException {
        String username = (String) authentication.getPrincipal();
        return ResponseEntity.ok().body(expenseCategoryService.getTopExpenseCategory(username, minDate, maxDate));
    }

    @GetMapping("/expanseCategories2/topIncomeCategory")
    public ResponseEntity<ExpenseCategoryTotalAmountWrapper> getTopIncomeCategory(Authentication authentication, @RequestParam("minDate") String minDate, @RequestParam("maxDate") String maxDate) throws ParseException {
        String username = (String) authentication.getPrincipal();
        return ResponseEntity.ok().body(expenseCategoryService.getTopIncomeCategory(username, minDate, maxDate));
    }

    @DeleteMapping("/expanseCategories2/{id}")
    public ResponseEntity<String> deleteByIdAndUsername(@PathVariable Long id, Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        return ResponseEntity.ok().body(expenseCategoryService.deleteByIdAndUsername(id, username));

    }


    @GetMapping("/expanseCategories2/{id}/expenses")
    public ResponseEntity<List<Expense>> getCategoryFilteredExpenses(Authentication authentication, @PathVariable Long id) {
        String username = (String) authentication.getPrincipal();
        return ResponseEntity.ok().body(expenseCategoryService.getCategoryFilteredExpenses(username, id));
    }

    @PatchMapping("/expanseCategories2/{id}")
    public ResponseEntity<String> editByIdAndUsername(@PathVariable Long id, @RequestBody Category category, Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        return ResponseEntity.ok().body(expenseCategoryService.editByIdAndUsername(id, category, username));
    }

    @PostMapping("/expanseCategories2")
    public ResponseEntity<String> addCategory(@RequestBody Category category, Authentication authentication){
        String username = (String) authentication.getPrincipal();
        category.setUsername(username);
        return ResponseEntity.ok().body(expenseCategoryService.addCategory(category));
    }

}
