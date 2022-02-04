package com.thesis.wallet.controllers;

import com.thesis.wallet.entity.Expanse;
import com.thesis.wallet.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class ExpenseController {
    private final ExpenseService expenseService;

    @GetMapping("/expanses2")
    public ResponseEntity<List<Expanse>> getAllExpenses(Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        return ResponseEntity.ok().body(expenseService.getAllExpenses(username));
    }

    @GetMapping("/expanses2/{id}")
    public ResponseEntity<Optional<Expanse>> getExpense(@PathVariable Long id) {
        return ResponseEntity.ok().body(expenseService.getExpense(id));
    }

}
