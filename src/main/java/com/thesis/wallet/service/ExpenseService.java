package com.thesis.wallet.service;

import com.thesis.wallet.DAO.ExpanseRepository;
import com.thesis.wallet.entity.Expanse;
import com.thesis.wallet.entity.security.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ExpenseService {
    private final ExpanseRepository expanseRepository;


   public List<Expanse> getAllExpenses(String username){
        return expanseRepository.findAllUserExpenses(username);
    }


    public Optional<Expanse> getExpense(Long id) {
       return expanseRepository.findById(id);
    }
}
