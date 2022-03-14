package com.thesis.wallet.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WalletTotalAmountWrapper {
    private Wallet wallet;
    private int amount;

    public WalletTotalAmountWrapper(Wallet wallet, int amount) {
        this.wallet = wallet;
        this.amount = amount;
    }

    public WalletTotalAmountWrapper() {

    }
}
