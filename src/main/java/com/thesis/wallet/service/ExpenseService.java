package com.thesis.wallet.service;

import com.thesis.wallet.DAO.ExpenseRepository;
import com.thesis.wallet.entity.Category;
import com.thesis.wallet.entity.Expense;
import com.thesis.wallet.entity.Wallet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ExpenseService {

    @Autowired
    private final ExpenseRepository expenseRepository;

    public List<Expense> getAllExpenses(String username){
        return expenseRepository.findAllUserExpenses(username);
    }

    public Optional<Expense> getExpense(Long id, String username) {
       return expenseRepository.findById(id, username);
    }

    public Optional<Category> getCategoryForExpense(Long id, String username) {
        return expenseRepository.findExpenseCategory(id, username);
    }

    public Optional<Wallet> getWalletForExpense(Long id, String username) {
        return expenseRepository.findExpenseWallet(id, username);
    }

    public String deleteByIdAndUsername(Long id, String username){
        expenseRepository.deleteByIdAndUsername(id, username);
        return "Process of delition from the server started...";
    }

    public String editByIdAndUsername(Long id, Expense expense, String username) {
       // expenseRepository.editByIdAndUsername(id, category.getExpanseCategoryName(), category.getIcon(), category.getType(), username);
        expenseRepository.editByIdAndUsername(id, expense.getName(), expense.getAmount(), expense.getType(), expense.getCategory().getId(), expense.getWallet().getId(), expense.getDate(), expense.getComments(), expense.getLocation(), username);
        return "Process of editing the category on the server started...";
    }

    public String addTransaction(Expense expense){
        expenseRepository.saveAndFlush(expense);
       return "";
    }

    public Expense getTopTransaction(String username, String start, String end, String type) throws ParseException {
        Date startDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse(start);
        Date endDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(end);
        List<Expense> expenses = expenseRepository.findAllUserExpenses(username).stream().filter(e -> e.getDate().after(startDate)).filter(e -> e.getDate().before(endDate)).filter(e -> e.getType().equals(type)).collect(Collectors.toList());
        if (expenses.size() == 0) {
            return new Expense();
        }
        Expense maxExpense = expenses.get(0);
        int maxAmount = maxExpense.getAmount();
        for(Expense expense : expenses){
            if (expense.getType().equals(type)) {
                if(expense.getAmount() > maxAmount){
                    maxExpense = expense;
                    maxAmount = maxExpense.getAmount();
                }
            }
        }
        return maxExpense;
    }


}
