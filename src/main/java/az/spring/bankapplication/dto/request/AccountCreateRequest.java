package az.spring.bankapplication.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreateRequest {

    @NotNull
    private Long fkUserId;

    @NotBlank
    private String accountNumber;

    @NotNull
    private BigDecimal balance;

}
