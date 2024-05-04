package az.spring.bankapplication.service.scheduledService;

import az.spring.bankapplication.entity.Currency;
import az.spring.bankapplication.dto.feignDto.CurrencyRequest;
import az.spring.bankapplication.dto.feignDto.ExchangeRateResponse;
import az.spring.bankapplication.repository.CurrencyRepository;
import az.spring.bankapplication.service.currencyService.CurrencyRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduledService {

    private final CurrencyRepository currencyRepository;

    private final CurrencyRateService currencyRateService;

    public void saveCurrencyToDataBase() {
        CurrencyRequest request = CurrencyRequest.builder()
                .base("AZN")
                .amount(BigDecimal.ONE)
                .build();
        ExchangeRateResponse currencyRate = currencyRateService.getCurrencyRate(request);

        LocalDateTime date = currencyRate.getDate();
        Map<String, BigDecimal> rates = currencyRate.getRates();

        for (Map.Entry<String, BigDecimal> rate : rates.entrySet()) {

            Currency currency = new Currency();
            currency.setUpdatedDate(date);
            currency.setRate(rate.getValue());
            currency.setCurrencyType(rate.getKey());
            currencyRepository.save(currency);
        }
    }


//    @Scheduled(cron = "0 */2 * * * *")
    public void updateCurrencyRates() {
        List<Currency> currencies = currencyRepository.findAll();

        CurrencyRequest request = CurrencyRequest.builder()
                .base("AZN")
                .amount(BigDecimal.ONE)
                .build();
        ExchangeRateResponse currencyRate = currencyRateService.getCurrencyRate(request);

        LocalDateTime date = currencyRate.getDate();
        Map<String, BigDecimal> latestRates = currencyRate.getRates();

        for (Currency currency : currencies) {
            String currencyType = currency.getCurrencyType();
            BigDecimal latestRate = latestRates.get(currencyType);

            if (latestRate == null) {
                currencyRepository.delete(currency);
            } else {
                currency.setUpdatedDate(date);
                currency.setRate(latestRate);
                currencyRepository.save(currency);
            }
        }
    }

}
