package com.thesis.wallet.controllers;

import com.thesis.wallet.DAO.ExpanseCategoryRepository;
import com.thesis.wallet.entity.Expanse;
import com.thesis.wallet.entity.ExpanseCategory;
import com.thesis.wallet.service.ExpenseCategoriesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class ExpenseCategoryController {
    private final ExpenseCategoriesService expenseCategoryService;

    @GetMapping("/expanseCategories2")
    public ResponseEntity<List<ExpanseCategory>> getAllExpenseCategories() {
        return ResponseEntity.ok().body(expenseCategoryService.getAllExpenseCategories());
    }


}
