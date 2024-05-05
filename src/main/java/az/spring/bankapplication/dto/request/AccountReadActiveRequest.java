package az.spring.bankapplication.dto.request;

import az.spring.bankapplication.enums.AccountStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import static az.spring.bankapplication.enums.AccountStatus.ACTIVE;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountReadActiveRequest {

    @NotNull
    private Long fkUserId;

}
