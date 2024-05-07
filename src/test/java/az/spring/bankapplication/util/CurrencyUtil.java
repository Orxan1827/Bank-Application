package az.spring.bankapplication.util;

import az.spring.bankapplication.dto.feignDto.CurrencyRequest;
import az.spring.bankapplication.dto.feignDto.ExchangeRateResponse;
import az.spring.bankapplication.dto.response.CurrencyResponse;
import az.spring.bankapplication.entity.Currency;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrencyUtil {

    public static Map<String, BigDecimal> rates() {
        Map<String, BigDecimal> rates = new HashMap<>();
        rates.put("USD", BigDecimal.valueOf(0.58));
        return rates;
    }

    public static ExchangeRateResponse exchangeRateResponse(Map<String, BigDecimal> rates) {
        return ExchangeRateResponse.builder()
                .base("AZN")
                .date(LocalDateTime.now())
                .rates(rates)
                .build();
    }

    public static CurrencyRequest CurrencyRequest() {
        return CurrencyRequest.builder()
                .currencies("USD")
                .base("AZN")
                .amount(BigDecimal.ONE)
                .build();
    }

    public static List<Currency> currencyList() {
        List<Currency> currencyList = new ArrayList<>();
        currencyList.add(Currency.builder().currencyType("USD").rate(BigDecimal.valueOf(0.58)).updatedDate(LocalDateTime.now()).build());
        currencyList.add(Currency.builder().currencyType("RUB").rate(BigDecimal.valueOf(50)).updatedDate(LocalDateTime.now()).build());
        return currencyList;
    }

    public static List<CurrencyResponse> currencyResponses() {
        List<CurrencyResponse> expectResponse = new ArrayList<>();
        expectResponse.add(CurrencyResponse.builder().currencyType("USD").rate(BigDecimal.valueOf(0.58)).build());
        expectResponse.add(CurrencyResponse.builder().currencyType("RUB").rate(BigDecimal.valueOf(50)).build());
        return expectResponse;
    }
}
