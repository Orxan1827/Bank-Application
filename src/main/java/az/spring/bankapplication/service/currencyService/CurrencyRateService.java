package az.spring.bankapplication.service.currencyService;

import az.spring.bankapplication.dto.feignDto.ExchangeRateResponse;
import az.spring.bankapplication.dto.feignDto.CurrencyRequest;
import az.spring.bankapplication.feignClient.ThirdPartyCurrencyServiceClient;
import az.spring.bankapplication.mapper.CurrencyMapper;
import az.spring.bankapplication.dto.response.CurrencyResponse;
import az.spring.bankapplication.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CurrencyRateService {

    private final ThirdPartyCurrencyServiceClient thirdPartyCurrencyServiceClient;

    private final CurrencyRepository currencyRepository;

    private final CurrencyMapper currencyMapper;

    public ExchangeRateResponse getCurrencyRate(CurrencyRequest currencyRequest) {
        return thirdPartyCurrencyServiceClient.getLatestExchangeRates(currencyRequest.getCurrencies(), currencyRequest.getBase(), currencyRequest.getAmount());
    }

    public List<CurrencyResponse> getMostSearchedCurrency() {
       return currencyRepository.findAll().stream()
                .filter(currency -> (List.of("USD", "EUR","RUB","TRY").contains(currency.getCurrencyType())))
                .map(currencyMapper::mapCurrencyToResponse)
                .collect(Collectors.toList());
    }

}
