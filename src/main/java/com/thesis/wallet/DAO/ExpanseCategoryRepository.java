package com.thesis.wallet.DAO;

import com.thesis.wallet.entity.ExpanseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableGlobalMethodSecurity(prePostEnabled = true)
public interface ExpanseCategoryRepository extends JpaRepository<ExpanseCategory,Long> {
}
