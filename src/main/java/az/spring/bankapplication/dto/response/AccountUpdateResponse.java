package az.spring.bankapplication.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountUpdateResponse {

    private Long id;
    private String accountNumber;
    private String status;
    private BigDecimal balance;
    private Long fkUserId;

}
