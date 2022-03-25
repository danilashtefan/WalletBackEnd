package com.thesis.wallet.service;

import com.thesis.wallet.DAO.ExpanseRepository;
import com.thesis.wallet.entity.Expanse;
import com.thesis.wallet.entity.ExpanseCategory;
import com.thesis.wallet.entity.Wallet;
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

    public Optional<Expanse> getExpense(Long id, String username) {
       return expanseRepository.findById(id, username);
    }

    public Optional<ExpanseCategory> getCategoryForExpense(Long id, String username) {
        return expanseRepository.findExpenseCategory(id, username);
    }

    public Optional<Wallet> getWalletForExpense(Long id, String username) {
        return expanseRepository.findExpenseWallet(id, username);
    }

    public String deleteByIdAndUsername(Long id, String username){
        expanseRepository.deleteByIdAndUsername(id, username);
        return "Process of delition from the server started...";
    }

    public String editByIdAndUsername(Long id, Expanse expense, String username) {
       // expanseRepository.editByIdAndUsername(id, category.getExpanseCategoryName(), category.getIcon(), category.getType(), username);
        expanseRepository.editByIdAndUsername(id, expense.getName(), expense.getAmount(), expense.getType(), expense.getCategory().getId(), expense.getWallet().getId(), expense.getDate(), expense.getComments(), expense.getLocation(), username);
        return "Process of editing the category on the server started...";
    }

    public String addTransaction(Expanse expense){
       //expanseRepository.addTransaction(expense.getName(), expense.getAmount(), expense.getType(),expense.getCategory(), expense.getWallet(), expense.getDate(), expense.getComments(), expense.getLocation());
        expanseRepository.saveAndFlush(expense);
       return "";
    }


}
