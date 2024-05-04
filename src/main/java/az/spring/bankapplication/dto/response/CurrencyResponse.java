package az.spring.bankapplication.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyResponse {

    private String currencyType;
    private BigDecimal rate;
}
