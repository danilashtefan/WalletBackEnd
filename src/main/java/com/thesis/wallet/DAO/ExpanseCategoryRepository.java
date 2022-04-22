package com.thesis.wallet.DAO;

import com.thesis.wallet.entity.Expense;
import com.thesis.wallet.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import javax.transaction.Transactional;
import java.util.List;

@EnableGlobalMethodSecurity(prePostEnabled = true)
public interface ExpanseCategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findAll();

    @Query("select c from Category c where c.username = ?#{[0]}")
    List<Category> findAllUserExpenseCategories(String username);

    @Query("select c from Category c where c.username = ?#{[0]} and c.id = ?#{[1]}")
    Category findCategoryById(String username, Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Category set expanseCategoryName =?#{[1]}, icon = ?#{[2]}, type = ?#{[3]}" +
            " where  id= ?#{[0]} and username = ?#{[4]}")
    void editByIdAndUsername(Long id, String name, String icon, String type, String username);

    @Transactional
    @Modifying
    @Query("delete from Category e where e.id = ?#{[0]} and e.username = ?#{[1]}")
    void deleteByIdAndUsername(Long id, String username);

    @Query("select expenses from Category e where e.username = ?#{[0]} and e.id = ?#{[1]}")
    List<Expense> findAllCategoriesExpenses(String username, Long id);
}
