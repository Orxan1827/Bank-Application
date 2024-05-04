package az.spring.bankapplication.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountReadRequest {

    @NotNull
    private Long fkUserId;

}
