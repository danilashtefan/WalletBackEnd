package com.thesis.wallet.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpenseCategoryTotalAmountWrapper {
    private ExpanseCategory category;
    private int amount;

    public ExpenseCategoryTotalAmountWrapper(ExpanseCategory category, int amount) {
        this.category = category;
        this.amount = amount;
    }

    public ExpenseCategoryTotalAmountWrapper() {

    }

}
