package com.thesis.wallet.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpenseCategoryTotalAmountWrapper {
    private ExpanseCategory category;
    private int expenseAmount;
    private int incomeAmount;

    public ExpenseCategoryTotalAmountWrapper(ExpanseCategory category, int expenseAmount, int incomeAmount) {
        this.category = category;
        this.expenseAmount = expenseAmount;
        this.incomeAmount = incomeAmount;
    }

    public ExpenseCategoryTotalAmountWrapper() {

    }

}
