package com.thesis.wallet.DAO;


import com.thesis.wallet.entity.Category;
import com.thesis.wallet.entity.Expense;
import com.thesis.wallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@EnableGlobalMethodSecurity(prePostEnabled = true)
public interface ExpanseRepository extends JpaRepository<Expense,Long> {

    @Query("select e from Expense e where e.username = ?#{[0]}")
    List<Expense> findAllUserExpenses(String username);

    @Query("select category from Expense e where e.id = ?#{[0]} and e.username = ?#{[1]}")
    Optional<Category> findExpenseCategory(Long id, String username);

    @Query("select wallet from Expense e where e.id = ?#{[0]} and e.username = ?#{[1]}")
    Optional<Wallet> findExpenseWallet(Long id, String username);

    @Transactional
    @Modifying
    @Query("delete from Expense e where e.id = ?#{[0]} and e.username = ?#{[1]}")
    void deleteByIdAndUsername(Long id, String username);

    @Query("select e from Expense e where e.id = ?#{[0]} and e.username = ?#{[0]}")
    Optional<Expense> findById(Long id, String username);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Expense set name =?#{[1]}, amount = ?#{[2]}, type = ?#{[3]}, category_id = ?#{[4]}, wallet_id = ?#{[5]}, " +
            "date = ?#{[6]}, comments = ?#{[7]}, location = ?#{[8]}" +
            " where  id= ?#{[0]} and username = ?#{[9]}")
    void editByIdAndUsername(Long id, String name, Integer amount, String type, Long categoryId, Long walletId, Date date, String comments, String location, String username);

}
