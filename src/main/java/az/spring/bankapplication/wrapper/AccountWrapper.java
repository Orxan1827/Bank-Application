package az.spring.bankapplication.wrapper;

import az.spring.bankapplication.enums.AccountStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AccountWrapper {

    private Long id;
    private int accountNumber;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    private BigDecimal balance;
    private Long fkUserId;

    public AccountWrapper(Long id, int accountNumber, AccountStatus status, BigDecimal balance, Long fkUserId) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.status = status;
        this.balance = balance;
        this.fkUserId = fkUserId;
    }

}
