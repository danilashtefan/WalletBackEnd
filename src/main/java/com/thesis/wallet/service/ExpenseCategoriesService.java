package com.thesis.wallet.service;

import com.thesis.wallet.DAO.ExpanseCategoryRepository;
import com.thesis.wallet.entity.Category;
import com.thesis.wallet.entity.Expense;
import com.thesis.wallet.entity.ExpenseCategoryTotalAmountWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ExpenseCategoriesService {
    private final ExpanseCategoryRepository expanseCategoryRepository;

    public List<Category> getAllExpenseCategories(String username) {
        return expanseCategoryRepository.findAllUserExpenseCategories(username);
    }

    public Category getCategoryById(String username, Long id){
        return expanseCategoryRepository.findCategoryById(username,id);
    }

    public List<ExpenseCategoryTotalAmountWrapper> getExpenseCategoriesWithAmounts(String username, String start, String end) throws ParseException {
        Date startDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse(start);
        Date endDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(end);
        List<Category> expenseCategories = expanseCategoryRepository.findAllUserExpenseCategories(username);
        ArrayList<ExpenseCategoryTotalAmountWrapper> resultLits = new ArrayList<ExpenseCategoryTotalAmountWrapper>();
        if (expenseCategories.size() == 0) {
            return resultLits;
        }
        for (int i = 0; i < expenseCategories.size(); i++) {
            int totalExpenses = 0;
            int totalIncomes = 0;
            Category category = expenseCategories.get(i);
            Set<Expense> categoryExpenses = category.getExpenses().stream().filter(e -> e.getDate().after(startDate)).filter(e -> e.getDate().before(endDate)).collect(Collectors.toSet());
            for (Expense expense : categoryExpenses) {
                if (expense.getType().equals("Expense")) {
                    totalExpenses += expense.getAmount();
                } else if (expense.getType().equals("Income")) {
                    totalIncomes += expense.getAmount();
                }
            }
            resultLits.add(new ExpenseCategoryTotalAmountWrapper(category, totalExpenses, totalIncomes, categoryExpenses));
        }
        return resultLits;
    }

    public ExpenseCategoryTotalAmountWrapper getTopExpenseCategory(String username, String start, String end) throws ParseException {
        Date startDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse(start);
        Date endDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(end);
        List<Category> expenseCategories = expanseCategoryRepository.findAllUserExpenseCategories(username);
        if (expenseCategories.size() == 0) {
            return new ExpenseCategoryTotalAmountWrapper();
        }
        Category maxExpenseCategory = expenseCategories.get(0);
        int maxExpenses = 0;
        Set<Expense> expenses = new HashSet<Expense>();
        for (int i = 0; i < expenseCategories.size(); i++) {
            int totalExpenses = 0;
            Set<Expense> categoryExpenses = expenseCategories.get(i).getExpenses().stream().filter(e -> e.getDate().after(startDate)).filter(e -> e.getDate().before(endDate)).collect(Collectors.toSet());;
            for (Expense expense : categoryExpenses) {
                if (expense.getType().equals("Expense")) {
                    totalExpenses += expense.getAmount();
                }
            }
            if (totalExpenses > maxExpenses) {
                maxExpenseCategory = expenseCategories.get(i);
                maxExpenses = totalExpenses;
                expenses = categoryExpenses;
            }
        }
        return new ExpenseCategoryTotalAmountWrapper(maxExpenseCategory, maxExpenses, 0, expenses);
    }

    public ExpenseCategoryTotalAmountWrapper getTopIncomeCategory(String username, String start, String end) throws ParseException {
        Date startDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse(start);
        Date endDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(end);
        List<Category> expenseCategories = expanseCategoryRepository.findAllUserExpenseCategories(username);
        if (expenseCategories.size() == 0) {
            return new ExpenseCategoryTotalAmountWrapper();
        }
        Category maxExpenseCategory = expenseCategories.get(0);
        int maxExpenses = 0;
        Set<Expense> expenses = new HashSet<Expense>();
        for (int i = 0; i < expenseCategories.size(); i++) {
            int totalExpenses = 0;
            Set<Expense> categoryExpenses = expenseCategories.get(i).getExpenses().stream().filter(e -> e.getDate().after(startDate)).filter(e -> e.getDate().before(endDate)).collect(Collectors.toSet());;
            for (Expense expense : categoryExpenses) {
                if (expense.getType().equals("Income")) {

                    totalExpenses += expense.getAmount();
                }
            }
            if (totalExpenses > maxExpenses) {
                maxExpenseCategory = expenseCategories.get(i);
                maxExpenses = totalExpenses;
                expenses = categoryExpenses;
            }
        }
        return new ExpenseCategoryTotalAmountWrapper(maxExpenseCategory, 0, maxExpenses, expenses);
    }

    public String editByIdAndUsername(Long id, Category category, String username) {
        expanseCategoryRepository.editByIdAndUsername(id, category.getExpanseCategoryName(), category.getIcon(), category.getType(), username);
        return "Process of editing the category on the server started...";
    }

    public List<Expense> getCategoryFilteredExpenses(String username, Long id) {
        return expanseCategoryRepository.findAllCategoriesExpenses(username, id);
    }

    public String deleteByIdAndUsername(Long id, String username) {
        expanseCategoryRepository.deleteByIdAndUsername(id, username);
        return "Process of delition from the server started...";
    }

    public String addCategory(Category category){
        expanseCategoryRepository.saveAndFlush(category);
        return "";
    }



}
