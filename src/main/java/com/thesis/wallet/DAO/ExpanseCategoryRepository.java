package com.thesis.wallet.DAO;

import com.thesis.wallet.entity.Expanse;
import com.thesis.wallet.entity.ExpanseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import javax.transaction.Transactional;
import java.util.List;

@EnableGlobalMethodSecurity(prePostEnabled = true)
public interface ExpanseCategoryRepository extends JpaRepository<ExpanseCategory,Long> {
    List<ExpanseCategory> findAll();

    @Query("select c from ExpanseCategory c where c.username = ?#{[0]}")
    List<ExpanseCategory> findAllUserExpenseCategories(String username);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update ExpanseCategory set expanseCategoryName =?#{[1]}, icon = ?#{[2]}, type = ?#{[3]}" +
            " where  id= ?#{[0]} and username = ?#{[4]}")
    void editByIdAndUsername(Long id, String name, String icon, String type, String username);

    @Transactional
    @Modifying
    @Query("delete from ExpanseCategory e where e.id = ?#{[0]} and e.username = ?#{[1]}")
    void deleteByIdAndUsername(Long id, String username);

    @Query("select expanses from ExpanseCategory e where e.username = ?#{[0]} and e.id = ?#{[1]}")
    List<Expanse> findAllCategoriesExpenses(String username, Long id);
}
