package com.thesis.wallet.service;


import com.thesis.wallet.DAO.WalletRepository;
import com.thesis.wallet.entity.*;
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
public class WalletService {
    private final WalletRepository walletRepository;

    public List<Wallet> getAllWallets(String username) {
        return walletRepository.findAllUserWallets(username);
    }

    public Wallet getWalletById(String username, Long id) {
        return walletRepository.findWalletById(username, id);
    }

    public String editByIdAndUsername(Long id, Wallet wallet, String username) {
        walletRepository.editByIdAndUsername(id, wallet.getWalletName(), wallet.getIcon(), wallet.getCurrency(), username);
        return "Process of editing the wallet on the server started...";
    }

    public List<WalletTotalAmountWrapper> getWalletsWithAmounts(String username, String start, String end) throws ParseException {
        Date startDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse(start);
        Date endDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(end);
        List<Wallet> wallets = walletRepository.findAllUserWallets(username);
        ArrayList<WalletTotalAmountWrapper> resultLits = new ArrayList<>();
        if (wallets.size() == 0) {
            return resultLits;
        }
        for (int i = 0; i < wallets.size(); i++) {
            int totalExpenses = 0;
            int totalIncomes = 0;
            Wallet wallet = wallets.get(i);
            Set<Expense> walletsExpenses = wallet.getExpenses().stream().filter(e -> e.getDate().after(startDate)).filter(e -> e.getDate().before(endDate)).collect(Collectors.toSet());
            for (Expense expense : walletsExpenses) {
                if (expense.getType().equals("Expense")) {
                    totalExpenses += expense.getAmount();
                } else {
                    totalIncomes += expense.getAmount();
                }
            }
            resultLits.add(new WalletTotalAmountWrapper(wallet, totalExpenses, totalIncomes));
        }
        return resultLits;
    }

    public WalletTotalAmountWrapper getTopExpenseWallet(String username, String start, String end) throws ParseException {
        Date startDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse(start);
        Date endDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(end);
        List<Wallet> wallets = walletRepository.findAllUserWallets(username);
        if (wallets.size() == 0) {
            return new WalletTotalAmountWrapper();
        }
        Wallet maxWallet = wallets.get(0);
        int maxExpenses = 0;
        for (int i = 0; i < wallets.size(); i++) {
            int totalExpenses = 0;
            Set<Expense> walletExpenses = wallets.get(i).getExpenses().stream().filter(e -> e.getDate().after(startDate)).filter(e -> e.getDate().before(endDate)).collect(Collectors.toSet());
            for (Expense expense : walletExpenses) {
                if (expense.getType().equals("Expense")) {
                    totalExpenses += expense.getAmount();
                }
            }
            if (totalExpenses > maxExpenses) {
                maxWallet = wallets.get(i);
                maxExpenses = totalExpenses;
            }
        }
        return new WalletTotalAmountWrapper(maxWallet, maxExpenses, 0);
    }

    public WalletTotalAmountWrapper getTopIncomeWallet(String username, String start, String end) throws ParseException {
        Date startDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse(start);
        Date endDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(end);
        List<Wallet> wallets = walletRepository.findAllUserWallets(username);
        if (wallets.size() == 0) {
            return new WalletTotalAmountWrapper();
        }
        Wallet maxWallet = wallets.get(0);
        int maxIncome = 0;
        for (int i = 0; i < wallets.size(); i++) {
            int totalExpenses = 0;
            Set<Expense> walletExpenses = wallets.get(i).getExpenses().stream().filter(e -> e.getDate().after(startDate)).filter(e -> e.getDate().before(endDate)).collect(Collectors.toSet());
            for (Expense expense : walletExpenses) {
                if (expense.getType().equals("Income")) {
                    totalExpenses += expense.getAmount();
                }
            }
            if (totalExpenses > maxIncome) {
                maxWallet = wallets.get(i);
                maxIncome = totalExpenses;
            }
        }
        return new WalletTotalAmountWrapper(maxWallet, 0, maxIncome);
    }

    public List<Expense> getWalletFilteredExpenses(String username, Long id) {
        return walletRepository.findAllWalletsExpenses(username, id);
    }

    public String deleteByIdAndUsername(Long id, String username) {
        walletRepository.deleteByIdAndUsername(id, username);
        return "Process of delition from the server started...";
    }

    public String addWallet(Wallet wallet){
        walletRepository.saveAndFlush(wallet);
        return "";
    }
}
