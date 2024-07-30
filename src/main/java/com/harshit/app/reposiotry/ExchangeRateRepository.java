package com.harshit.app.reposiotry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harshit.app.model.ExchangeRate;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
	
    List<ExchangeRate> findFirstByTargetCurrencyOrderByDateDesc(String targetCurrency);
    
    List<ExchangeRate> findTop3ByTargetCurrencyOrderByDateDesc(String targetCurrency);
}
