package com.thesis.wallet.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WalletTotalAmountWrapper {
    private Wallet wallet;
    private int expenseAmount;
    private int incomeAmount;

    public WalletTotalAmountWrapper(Wallet wallet, int expenseAmount, int incomeAmount) {
        this.wallet = wallet;
        this.expenseAmount = expenseAmount;
        this.incomeAmount = incomeAmount;
    }

    public WalletTotalAmountWrapper() {

    }
}
