package com.harshit.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harshit.app.service.ExchangeRateService;

@RestController
@RequestMapping("/fx")
public class ExchangeRateController {

	@Autowired
	private ExchangeRateService exchangeRateService;
	
	
	/* GET /fx: Returns fx rates from USD to EUR, GBP, JPY, CZK. Response should contain 
	date of conversion, source currency, target currency, exchange rate. It should be possible to 
	specify target currency.*/
	
	@GetMapping
	public ResponseEntity<?> getExchangeRates(@RequestParam(required = false) String to) {
		return ResponseEntity.ok(exchangeRateService.getExchangeRates(to));
	}

	/* GET /fx/{targetCurrency}: Returns 3 latest fx rates in form of time series from 
USD to target currency with a step of 1 day.*/
	
	@GetMapping("/{targetCurrency}")
	public ResponseEntity<?> getLatestExchangeRates(@PathVariable String targetCurrency) {
		return ResponseEntity.ok(exchangeRateService.getLatestExchangeRates(targetCurrency));
	}
}
