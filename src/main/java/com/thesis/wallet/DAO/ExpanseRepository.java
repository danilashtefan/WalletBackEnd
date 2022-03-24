package com.thesis.wallet.DAO;


import com.thesis.wallet.entity.Expanse;
import com.thesis.wallet.entity.ExpanseCategory;
import com.thesis.wallet.entity.Wallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@EnableGlobalMethodSecurity(prePostEnabled = true)
public interface ExpanseRepository extends JpaRepository<Expanse,Long> {
//   @Override

    //Page<Expanse> findAll(Pageable pageable);


    @Query("select e from Expanse e where e.username = ?#{[0]}")
    List<Expanse> findAllUserExpenses(String username);

    @Query("select category from Expanse e where e.id = ?#{[0]} and e.username = ?#{[1]}")
    Optional<ExpanseCategory> findExpenseCategory(Long id, String username);

    @Query("select wallet from Expanse e where e.id = ?#{[0]} and e.username = ?#{[1]}")
    Optional<Wallet> findExpenseWallet(Long id, String username);

    @Transactional
    @Modifying
    @Query("delete from Expanse e where e.id = ?#{[0]} and e.username = ?#{[1]}")
    void deleteByIdAndUsername(Long id, String username);

    @Query("select e from Expanse e where e.id = ?#{[0]} and e.username = ?#{[0]}")
    Optional<Expanse> findById(Long id, String username);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Expanse set name =?#{[1]}, amount = ?#{[2]}, type = ?#{[3]}, category_id = ?#{[4]}, wallet_id = ?#{[5]}, " +
            "date = ?#{[6]}, comments = ?#{[7]}, location = ?#{[8]}" +
            " where  id= ?#{[0]} and username = ?#{[9]}")
    void editByIdAndUsername(Long id, String name, Integer amount, String type, Long categoryId, Long walletId, Date date, String comments, String location, String username);

//    @Transactional
//    @Modifying
//    //@Query("INSERT INTO Expanse (name, amount, category_id, wallet_id, date, username) values (?#{[0]}, ?#{[1]}, ?#{[2]}, ?#{[3]}), ?#{[4]}, ?#{[5]})")
//    @Query(value = "insert into Expanse (name, amount, category_id, wallet_id, date, username) values (:name, :amount, :category_id, :wallet_id, :date, :username)")
//    Optional<Expanse> addExpense(String name, Integer amount, Long id, Long id1, Date date, String username);
}
