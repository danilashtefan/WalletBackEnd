package com.thesis.wallet.service;

import com.thesis.wallet.DAO.ExpanseCategoryRepository;
import com.thesis.wallet.entity.Expanse;
import com.thesis.wallet.entity.ExpanseCategory;
import com.thesis.wallet.entity.ExpenseCategoryTotalAmountWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ExpenseCategoriesService {
    private final ExpanseCategoryRepository expanseCategoryRepository;

    public List<ExpanseCategory> getAllExpenseCategories(String username) {
        return expanseCategoryRepository.findAllUserExpenseCategories(username);
    }

    public List<ExpenseCategoryTotalAmountWrapper> getExpenseCategoriesWithAmounts(String username){
        List<ExpanseCategory> expenseCategories = expanseCategoryRepository.findAllUserExpenseCategories(username);
        ArrayList<ExpenseCategoryTotalAmountWrapper> resultLits = new ArrayList<ExpenseCategoryTotalAmountWrapper>();
        if (expenseCategories.size() == 0) {
            return resultLits;
        }
        for (int i = 0; i < expenseCategories.size(); i++) {
            int totalExpenses = 0;
            int totalIncomes = 0;
            ExpanseCategory category = expenseCategories.get(i);
            Set<Expanse> categoryExpenses = category.getExpanses();
            for (Expanse expense : categoryExpenses) {
                if (expense.getType().equals("Expense")){
                    totalExpenses += expense.getAmount();
                }else{
                    totalIncomes += expense.getAmount();
                }
            }
            resultLits.add(new ExpenseCategoryTotalAmountWrapper(category, totalExpenses, totalIncomes));
        }
        return resultLits;
    }

    public ExpenseCategoryTotalAmountWrapper getTopExpenseCategory(String username) {
        List<ExpanseCategory> expenseCategories = expanseCategoryRepository.findAllUserExpenseCategories(username);
        if (expenseCategories.size() == 0) {
            return new ExpenseCategoryTotalAmountWrapper();
        }
        ExpanseCategory maxExpenseCategory = expenseCategories.get(0);
        int maxExpenses = 0;
        for (int i = 0; i < expenseCategories.size(); i++) {
            int totalExpenses = 0;
            Set<Expanse> categoryExpenses = expenseCategories.get(i).getExpanses();
            for (Expanse expense : categoryExpenses) {
                if (expense.getType().equals("Expense")){
                    totalExpenses += expense.getAmount();
                }
            }
            if (totalExpenses > maxExpenses) {
                maxExpenseCategory = expenseCategories.get(i);
                maxExpenses = totalExpenses;
            }
        }
        return new ExpenseCategoryTotalAmountWrapper(maxExpenseCategory, maxExpenses, 0);
    }

    public ExpenseCategoryTotalAmountWrapper getTopIncomeCategory(String username) {
        List<ExpanseCategory> expenseCategories = expanseCategoryRepository.findAllUserExpenseCategories(username);
        if (expenseCategories.size() == 0) {
            return new ExpenseCategoryTotalAmountWrapper();
        }
        ExpanseCategory maxExpenseCategory = expenseCategories.get(0);
        int maxExpenses = 0;
        for (int i = 0; i < expenseCategories.size(); i++) {
            int totalExpenses = 0;
            Set<Expanse> categoryExpenses = expenseCategories.get(i).getExpanses();
            for (Expanse expense : categoryExpenses) {
                if (expense.getType().equals("Income")){

                    totalExpenses += expense.getAmount();
                }
            }
            if (totalExpenses > maxExpenses) {
                maxExpenseCategory = expenseCategories.get(i);
                maxExpenses = totalExpenses;
            }
        }
        return new ExpenseCategoryTotalAmountWrapper(maxExpenseCategory, 0 ,maxExpenses);
    }

    public String editByIdAndUsername(Long id, ExpanseCategory category, String username) {
        expanseCategoryRepository.editByIdAndUsername(id, category.getExpanseCategoryName(), category.getIcon(), category.getType(), username);
        return "Process of editing the category on the server started...";
    }

    public List<Expanse> getCategoryFilteredExpenses(String username, Long id) {
        return expanseCategoryRepository.findAllCategoriesExpenses(username, id);
    }

    public String deleteByIdAndUsername(Long id, String username) {
        expanseCategoryRepository.deleteByIdAndUsername(id, username);
        return "Process of delition from the server started...";
    }


}
