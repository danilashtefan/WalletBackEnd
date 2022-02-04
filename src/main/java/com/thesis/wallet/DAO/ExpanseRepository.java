package com.thesis.wallet.DAO;


import com.thesis.wallet.entity.Expanse;
import com.thesis.wallet.entity.security.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import java.util.List;
import java.util.Optional;

@EnableGlobalMethodSecurity(prePostEnabled = true)
public interface ExpanseRepository extends JpaRepository<Expanse,Long> {
//   @Override

    Page<Expanse> findAll(Pageable pageable);


    @Query("select e from Expanse e where e.username = ?#{[0]}")
    List<Expanse> findAllUserExpenses(String username);

    Optional<Expanse> findById(Long id);
}
