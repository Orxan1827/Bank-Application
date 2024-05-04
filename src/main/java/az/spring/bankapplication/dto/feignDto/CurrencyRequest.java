package az.spring.bankapplication.dto.feignDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyRequest {

    @NotBlank
    private String currencies;

    @NotBlank
    private String base;

    @NotNull
    private BigDecimal amount;

}
