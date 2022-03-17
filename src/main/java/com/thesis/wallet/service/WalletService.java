package com.thesis.wallet.service;


import com.thesis.wallet.DAO.ExpanseRepository;
import com.thesis.wallet.DAO.WalletRepository;
import com.thesis.wallet.entity.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class WalletService {
    private final WalletRepository walletRepository;

    public List<Wallet> getAllWallets(String username) {
        return walletRepository.findAllUserWallets(username);
    }

    public Optional<Wallet> getWallet(Long id) {
        return walletRepository.findById(id);
    }

    public String editByIdAndUsername(Long id, Wallet wallet, String username) {
        walletRepository.editByIdAndUsername(id, wallet.getWalletName(), wallet.getIcon(), wallet.getCurrency(), username);
        return "Process of editing the wallet on the server started...";
    }

    public List<WalletTotalAmountWrapper> getWalletsWithAmounts(String username){
        List<Wallet> wallets = walletRepository.findAllUserWallets(username);
        ArrayList<WalletTotalAmountWrapper> resultLits = new ArrayList<>();
        if (wallets.size() == 0) {
            return resultLits;
        }
        for (int i = 0; i < wallets.size(); i++) {
            int totalExpenses = 0;
            int totalIncomes = 0;
            Wallet wallet = wallets.get(i);
            Set<Expanse> walletsExpenses = wallet.getExpanses();
            for (Expanse expense : walletsExpenses) {
                if (expense.getType().equals("Expense")){
                    totalExpenses += expense.getAmount();
                }else{
                    totalIncomes += expense.getAmount();
                }
            }
            resultLits.add(new WalletTotalAmountWrapper(wallet, totalExpenses, totalIncomes));
        }
        return resultLits;
    }

    public WalletTotalAmountWrapper getTopExpenseWallet(String username) {
        List<Wallet> wallets = walletRepository.findAllUserWallets(username);
        if (wallets.size() == 0) {
            return new WalletTotalAmountWrapper();
        }
        Wallet maxWallet = wallets.get(0);
        int maxExpenses = 0;
        for (int i = 0; i < wallets.size(); i++) {
            int totalExpenses = 0;
            Set<Expanse> walletExpenses = wallets.get(i).getExpanses();
            for (Expanse expense : walletExpenses) {
                if (expense.getType().equals("Expense")) {
                    totalExpenses += expense.getAmount();
                }
            }
            if (totalExpenses > maxExpenses) {
                maxWallet = wallets.get(i);
                maxExpenses = totalExpenses;
            }
        }
        return new WalletTotalAmountWrapper(maxWallet, maxExpenses,0);
    }

    public WalletTotalAmountWrapper getTopIncomeWallet(String username) {
        List<Wallet> wallets = walletRepository.findAllUserWallets(username);
        if (wallets.size() == 0) {
            return new WalletTotalAmountWrapper();
        }
        Wallet maxWallet = wallets.get(0);
        int maxIncome = 0;
        for (int i = 0; i < wallets.size(); i++) {
            int totalExpenses = 0;
            Set<Expanse> walletExpenses = wallets.get(i).getExpanses();
            for (Expanse expense : walletExpenses) {
                if (expense.getType().equals("Income")) {
                    totalExpenses += expense.getAmount();
                }
            }
            if (totalExpenses > maxIncome) {
                maxWallet = wallets.get(i);
                maxIncome = totalExpenses;
            }
        }
        return new WalletTotalAmountWrapper(maxWallet, 0,maxIncome);
    }

    public List<Expanse> getWalletFilteredExpenses(String username, Long id) {
        return walletRepository.findAllWalletsExpenses(username, id);
    }

    public String deleteByIdAndUsername(Long id, String username) {
        walletRepository.deleteByIdAndUsername(id, username);
        return "Process of delition from the server started...";
    }
}
