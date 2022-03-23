package com.thesis.wallet.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ExpenseCategoryTotalAmountWrapper {
    private ExpanseCategory category;
    private int expenseAmount;
    private int incomeAmount;
    private Set<Expanse> expenses;

    public ExpenseCategoryTotalAmountWrapper(ExpanseCategory category, int expenseAmount, int incomeAmount, Set<Expanse> expenses) {
        this.category = category;
        this.expenseAmount = expenseAmount;
        this.incomeAmount = incomeAmount;
        this.expenses = expenses;
    }

    public ExpenseCategoryTotalAmountWrapper() {

    }

}
