package az.spring.bankapplication.dto.request;

import az.spring.bankapplication.enums.AccountStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountUpdateRequest {

    @NotBlank
    private String accountNumber;

    @NotNull
    private AccountStatus status;

    @NotNull
    private BigDecimal balance;

    @NotNull
    private Long fkUserId;

}
