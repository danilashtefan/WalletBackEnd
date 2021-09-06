package com.thesis.wallet.DAO;

import com.thesis.wallet.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CurrencyRepository extends JpaRepository<Currency,Long> {
}
