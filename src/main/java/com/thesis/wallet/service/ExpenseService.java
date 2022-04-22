package com.thesis.wallet.service;

import com.thesis.wallet.DAO.ExpanseRepository;
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
    private final ExpanseRepository expanseRepository;

    public List<Expense> getAllExpenses(String username){
        return expanseRepository.findAllUserExpenses(username);
    }

    public Optional<Expense> getExpense(Long id, String username) {
       return expanseRepository.findById(id, username);
    }

    public Optional<Category> getCategoryForExpense(Long id, String username) {
        return expanseRepository.findExpenseCategory(id, username);
    }

    public Optional<Wallet> getWalletForExpense(Long id, String username) {
        return expanseRepository.findExpenseWallet(id, username);
    }

    public String deleteByIdAndUsername(Long id, String username){
        expanseRepository.deleteByIdAndUsername(id, username);
        return "Process of delition from the server started...";
    }

    public String editByIdAndUsername(Long id, Expense expense, String username) {
       // expanseRepository.editByIdAndUsername(id, category.getExpanseCategoryName(), category.getIcon(), category.getType(), username);
        expanseRepository.editByIdAndUsername(id, expense.getName(), expense.getAmount(), expense.getType(), expense.getCategory().getId(), expense.getWallet().getId(), expense.getDate(), expense.getComments(), expense.getLocation(), username);
        return "Process of editing the category on the server started...";
    }

    public String addTransaction(Expense expense){
        expanseRepository.saveAndFlush(expense);
       return "";
    }

    public Expense getTopTransaction(String username, String start, String end, String type) throws ParseException {
        Date startDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse(start);
        Date endDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(end);
        List<Expense> expenses = expanseRepository.findAllUserExpenses(username).stream().filter(e -> e.getDate().after(startDate)).filter(e -> e.getDate().before(endDate)).collect(Collectors.toList());;
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
