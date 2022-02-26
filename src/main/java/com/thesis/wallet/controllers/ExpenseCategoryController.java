package com.thesis.wallet.controllers;

import com.thesis.wallet.DAO.ExpanseCategoryRepository;
import com.thesis.wallet.entity.Expanse;
import com.thesis.wallet.entity.ExpanseCategory;
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

    @PatchMapping("expanseCategories2/{id}")
    public ResponseEntity<String> editByIdAndUsername(@PathVariable Long id, @RequestBody ExpanseCategory category, Authentication authentication){
        String username = (String) authentication.getPrincipal();
        return ResponseEntity.ok().body(expenseCategoryService.editByIdAndUsername(id, category, username));

    }


}
