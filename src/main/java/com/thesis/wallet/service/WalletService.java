package com.thesis.wallet.service;


import com.thesis.wallet.DAO.ExpanseRepository;
import com.thesis.wallet.DAO.WalletRepository;
import com.thesis.wallet.entity.Expanse;
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
}
