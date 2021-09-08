package com.thesis.wallet.DAO;


import com.thesis.wallet.entity.Expanse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpanseRepository extends JpaRepository<Expanse,Long> {
    Long deleteByName(String name);
}
