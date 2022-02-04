package com.thesis.wallet.DAO;


import com.thesis.wallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableGlobalMethodSecurity(prePostEnabled = true)
public interface WalletRepository extends JpaRepository<Wallet,Long> {
}
