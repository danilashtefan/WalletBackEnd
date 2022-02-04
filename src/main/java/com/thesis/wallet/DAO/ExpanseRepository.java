package com.thesis.wallet.DAO;


import com.thesis.wallet.entity.Expanse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import java.util.List;
import java.util.Optional;

@EnableGlobalMethodSecurity(prePostEnabled = true)
public interface ExpanseRepository extends JpaRepository<Expanse,Long> {
//   @Override

    Page<Expanse> findAll(Pageable pageable);
    List<Expanse> findAll();
    Optional<Expanse> findById(Long id);
}
