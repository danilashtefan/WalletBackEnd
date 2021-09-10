package com.thesis.wallet.controllers;

import com.thesis.wallet.DAO.ExpanseRepository;
import com.thesis.wallet.entity.Expanse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExpansesController {
    private final ExpanseRepository repository;

    ExpansesController(ExpanseRepository repository){
        this.repository = repository;
    }

    @GetMapping("/expanses")
    List<Expanse> all(){
        return repository.findAll();
    }
}
