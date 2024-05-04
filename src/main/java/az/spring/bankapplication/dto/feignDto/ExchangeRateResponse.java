package az.spring.bankapplication.dto.feignDto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateResponse {

    private String base;
    private LocalDateTime date;
    private Map<String, BigDecimal> rates;
}
