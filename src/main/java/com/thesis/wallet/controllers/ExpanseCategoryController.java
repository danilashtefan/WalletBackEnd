package com.thesis.wallet.controllers;

import com.thesis.wallet.DAO.ExpanseCategoryRepository;
import com.thesis.wallet.DAO.ExpanseRepository;
import com.thesis.wallet.entity.Expanse;
import com.thesis.wallet.entity.ExpanseCategory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExpanseCategoryController {

    private final ExpanseCategoryRepository repository;

    ExpanseCategoryController(ExpanseCategoryRepository repository){
        this.repository = repository;
    }

    @GetMapping("/expanse-categories")
    List<ExpanseCategory> all(){
        return repository.findAll();
    }
}
