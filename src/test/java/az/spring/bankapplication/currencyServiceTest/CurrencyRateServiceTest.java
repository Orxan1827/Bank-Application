package az.spring.bankapplication.currencyServiceTest;

import az.spring.bankapplication.dto.feignDto.CurrencyRequest;
import az.spring.bankapplication.dto.feignDto.ExchangeRateResponse;
import az.spring.bankapplication.dto.response.CurrencyResponse;
import az.spring.bankapplication.entity.Currency;
import az.spring.bankapplication.feignClient.ThirdPartyCurrencyServiceClient;
import az.spring.bankapplication.mapper.CurrencyMapper;
import az.spring.bankapplication.repository.CurrencyRepository;
import az.spring.bankapplication.service.currencyService.CurrencyRateService;
import az.spring.bankapplication.util.CurrencyUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.quality.Strictness.LENIENT;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
public class CurrencyRateServiceTest {

    @Mock
    private ThirdPartyCurrencyServiceClient thirdPartyCurrencyServiceClient;

    @Mock
    private CurrencyRepository currencyRepository;

    @Mock
    private CurrencyMapper currencyMapper;

    @InjectMocks
    private CurrencyRateService currencyRateService;

    @Test
    public void testGetCurrencyRate_whenCurrencyRequest_thanReturnExchangeRateResponse() {
        Map<String, BigDecimal> rates = CurrencyUtil.rates();
        ExchangeRateResponse expect = CurrencyUtil.exchangeRateResponse(rates);
        CurrencyRequest request = CurrencyUtil.CurrencyRequest();

        when(thirdPartyCurrencyServiceClient.getLatestExchangeRates(request.getCurrencies(), request.getBase(), request.getAmount())).thenReturn(expect);

        ExchangeRateResponse actual = currencyRateService.getCurrencyRate(request);

        assertNotNull(actual);
        assertEquals(expect, actual);
        assertEquals(expect.getRates(), actual.getRates());

        verify(thirdPartyCurrencyServiceClient,times(1)).getLatestExchangeRates(request.getCurrencies(), request.getBase(), request.getAmount());
    }

    @Test
    public void testGetMostSearchCurrency_whenSuccess_returnCurrencyResponseList() {
        List<Currency> currencyList = CurrencyUtil.currencyList();
        List<CurrencyResponse> expectResponse = CurrencyUtil.currencyResponses();
        when(currencyRepository.findAll()).thenReturn(currencyList);
        when(currencyMapper.mapCurrencyToResponse(any(Currency.class))).thenReturn(expectResponse.get(0), expectResponse.get(1));

        List<CurrencyResponse> actual = currencyRateService.getMostSearchedCurrency();

        assertNotNull(actual);
        assertEquals(expectResponse, actual);
        assertEquals(expectResponse.get(0), actual.get(0));

        verify(currencyRepository,times(1)).findAll();
        verify(currencyMapper,times(2)).mapCurrencyToResponse(any(Currency.class));
    }

}
