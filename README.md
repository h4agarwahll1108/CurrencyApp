# CurrencyApp
For runing this app
java 17 - required
mysql - required
#Steps
1. First open this project into IDE.
2. In application properties file update the mysql user details and create schema in mysql name as fxrates.
3. Now run the application as a spring boot app or using cli mvn spring-boot:run.
4. Hit the API--> http://localhost:8080/fx [RESPONSE-it will show all targetCurrency with their rates]
5. Hit the API--> http://localhost:8080/fx/USD [RESPONSE-it will show top 3 targetCurrency with their rates]

#Code explaination 
ExchangeRate-->entity table created with attributes id,rate,sourceCurrency, targetCurrency
ExchangeRateResponse-->DTO with attribute date , base , rates. This will fetch the data from the external api.
ExchangeRateRepository-->for filtering the data.
ExchangeRateService-->Logic to fetch the data and save in db.
ExchangeRateController-->API calling.
Migration Scripts-->Flyway will automatically pick up migration scripts and run them on application startup.
