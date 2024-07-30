package com.harshit.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.harshit.app.dto.ExchangeRateResponse;
import com.harshit.app.model.ExchangeRate;
import com.harshit.app.reposiotry.ExchangeRateRepository;

@Service
public class ExchangeRateService {

    private static final String BASE_URL = "https://api.frankfurter.app";
    
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;
    
    

    public List<ExchangeRate> getExchangeRates(String to) {
        if (exchangeRateRepository.count() == 0) {
        	fetchAndStoreHistoricalExchangeRates();
            fetchAndStoreExchangeRates();
        }
        if (to == null) {
            return exchangeRateRepository.findAll();
        }
        return exchangeRateRepository.findFirstByTargetCurrencyOrderByDateDesc(to);
    }

    public List<ExchangeRate> getLatestExchangeRates(String targetCurrency) {
        return exchangeRateRepository.findTop3ByTargetCurrencyOrderByDateDesc(targetCurrency);
    }

    private void fetchAndStoreExchangeRates() {
        ResponseEntity<ExchangeRateResponse> response = restTemplate.getForEntity(BASE_URL + "/latest", ExchangeRateResponse.class);
        ExchangeRateResponse exchangeRateResponse = response.getBody();
        if (exchangeRateResponse != null) {
            exchangeRateResponse.getRates().forEach((currency, rate) -> {
                ExchangeRate exchangeRate = new ExchangeRate();
                exchangeRate.setDate(exchangeRateResponse.getDate());
                exchangeRate.setSourceCurrency("USD");
                exchangeRate.setTargetCurrency(currency);
                exchangeRate.setRate(rate);
                exchangeRateRepository.save(exchangeRate);
            });
        }
    }
    private void fetchAndStoreHistoricalExchangeRates() {
        ResponseEntity<ExchangeRateResponse> response = restTemplate.getForEntity(BASE_URL + "/2024-03-18?to=USD", ExchangeRateResponse.class);
        ExchangeRateResponse exchangeRateResponse = response.getBody();
        if (exchangeRateResponse != null) {
            exchangeRateResponse.getRates().forEach((currency, rate) -> {
                ExchangeRate exchangeRate = new ExchangeRate();
                exchangeRate.setDate(exchangeRateResponse.getDate());
                exchangeRate.setSourceCurrency("USD");
                exchangeRate.setTargetCurrency(currency);
                exchangeRate.setRate(rate);
                exchangeRateRepository.save(exchangeRate);
            });
        }
    }
}
