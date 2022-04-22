package com.thesis.wallet.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ExpenseCategoryTotalAmountWrapper {
    private Category category;
    private int expenseAmount;
    private int incomeAmount;
    private Set<Expense> expenses;

    public ExpenseCategoryTotalAmountWrapper(Category category, int expenseAmount, int incomeAmount, Set<Expense> expenses) {
        this.category = category;
        this.expenseAmount = expenseAmount;
        this.incomeAmount = incomeAmount;
        this.expenses = expenses;
    }

    public ExpenseCategoryTotalAmountWrapper() {

    }

}
