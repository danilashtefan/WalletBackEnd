package com.thesis.wallet.DAO;


import com.thesis.wallet.entity.Expanse;
import com.thesis.wallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@EnableGlobalMethodSecurity(prePostEnabled = true)
public interface WalletRepository extends JpaRepository<Wallet,Long> {

    @Query("select w from Wallet w where w.username = ?#{[0]}")
    List<Wallet> findAllUserWallets(String username);

    @Query("select w from Wallet w where w.username = ?#{[0]} and w.id = ?#{[1]}")
    Wallet findWalletById(String username, Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Wallet set walletName = ?#{[1]}, icon = ?#{[2]}, currency = ?#{[3]}" +
            " where  id= ?#{[0]} and username = ?#{[4]}")
    void editByIdAndUsername(Long id, String name, String icon, String type, String username);

    @Transactional
    @Modifying
    @Query("delete from Wallet e where e.id = ?#{[0]} and e.username = ?#{[1]}")
    void deleteByIdAndUsername(Long id, String username);

    @Query("select expanses from Wallet e where e.username = ?#{[0]} and e.id = ?#{[1]}")
    List<Expanse> findAllWalletsExpenses(String username, Long id);

}
