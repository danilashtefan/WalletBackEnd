package com.thesis.wallet.DAO;

import com.thesis.wallet.entity.ExpanseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


public interface ExpanseCategoryRepository extends JpaRepository<ExpanseCategory,Long> {
}
