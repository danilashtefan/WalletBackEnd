package com.thesis.wallet.DAO;


import com.thesis.wallet.entity.Expanse;
import com.thesis.wallet.entity.ExpanseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface ExpanseRepository extends JpaRepository<Expanse,Long> {
    Expanse findByName(@Param("name") String name);

}
