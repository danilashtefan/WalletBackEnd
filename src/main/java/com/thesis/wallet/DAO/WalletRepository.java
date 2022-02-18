package com.thesis.wallet.DAO;


import com.thesis.wallet.entity.Expanse;
import com.thesis.wallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import java.util.List;

@EnableGlobalMethodSecurity(prePostEnabled = true)
public interface WalletRepository extends JpaRepository<Wallet,Long> {

    @Query("select w from Wallet w where w.username = ?#{[0]}")
    List<Wallet> findAllUserWallets(String username);
}
