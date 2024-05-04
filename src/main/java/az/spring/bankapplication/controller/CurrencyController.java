package az.spring.bankapplication.controller;

import az.spring.bankapplication.dto.feignDto.CurrencyRequest;
import az.spring.bankapplication.dto.feignDto.ExchangeRateResponse;
import az.spring.bankapplication.dto.response.CurrencyResponse;
import az.spring.bankapplication.service.currencyService.CurrencyRateService;
import az.spring.bankapplication.service.scheduledService.ScheduledService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/currencies")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyRateService currencyRateService;

    private final ScheduledService scheduledService;

    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @PostMapping("/currency")
    public ResponseEntity<ExchangeRateResponse> getCurrencyRate(@Valid @RequestBody CurrencyRequest request) {
        return ResponseEntity.status(OK).body(currencyRateService.getCurrencyRate(request));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    @ResponseStatus(CREATED)
    public void saveCurrency() {
        scheduledService.saveCurrencyToDataBase();
    }

    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<CurrencyResponse>> getAllCurrency() {
        return ResponseEntity.status(OK).body(currencyRateService.getMostSearchedCurrency());
    }

}
