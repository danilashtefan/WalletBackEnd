package com.thesis.wallet.service;


import com.thesis.wallet.DAO.ExpanseRepository;
import com.thesis.wallet.DAO.WalletRepository;
import com.thesis.wallet.entity.Expanse;
import com.thesis.wallet.entity.ExpanseCategory;
import com.thesis.wallet.entity.Wallet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class WalletService {
    private final WalletRepository walletRepository;



    public List<Wallet> getAllWallets(String username){
        return walletRepository.findAllUserWallets(username);
    }

    public Optional<Wallet> getWallet(Long id) {
        return walletRepository.findById(id);
    }

    public String editByIdAndUsername(Long id, Wallet wallet, String username){
        walletRepository.editByIdAndUsername(id, wallet.getWalletName(), wallet.getIcon(), wallet.getCurrency() , username);
        return "Process of editing the wallet on the server started...";
    }

    public List<Expanse> getWalletFilteredExpenses(String username, Long id){
        return walletRepository.findAllWalletsExpenses(username, id);
    }

    public String deleteByIdAndUsername(Long id, String username){
        walletRepository.deleteByIdAndUsername(id, username);
        return "Process of delition from the server started...";
    }
}
