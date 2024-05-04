package az.spring.bankapplication.feignClient;

import az.spring.bankapplication.dto.feignDto.ExchangeRateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(name = "ms-currency", url = "${client.ms-currency.endpoint}")
public interface ThirdPartyCurrencyServiceClient {

    @GetMapping
    public ExchangeRateResponse getLatestExchangeRates(@RequestParam String currencies, @RequestParam String base, @RequestParam BigDecimal amount);

}
