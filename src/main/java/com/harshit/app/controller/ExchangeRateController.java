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

	@GetMapping
	public ResponseEntity<?> getExchangeRates(@RequestParam(required = false) String to) {
		return ResponseEntity.ok(exchangeRateService.getExchangeRates(to));
	}

	@GetMapping("/{targetCurrency}")
	public ResponseEntity<?> getLatestExchangeRates(@PathVariable String targetCurrency) {
		return ResponseEntity.ok(exchangeRateService.getLatestExchangeRates(targetCurrency));
	}
}
