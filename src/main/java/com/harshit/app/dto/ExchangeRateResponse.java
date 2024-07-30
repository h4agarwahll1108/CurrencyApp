package com.harshit.app.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class ExchangeRateResponse {

	private LocalDate date;
	
	private String base;
	
	private Map<String, BigDecimal> rates;

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public Map<String, BigDecimal> getRates() {
		return rates;
	}

	public void setRates(Map<String, BigDecimal> rates) {
		this.rates = rates;
	}
}
