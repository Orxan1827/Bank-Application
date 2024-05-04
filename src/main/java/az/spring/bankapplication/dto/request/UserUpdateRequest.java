package az.spring.bankapplication.dto.request;

import az.spring.bankapplication.constraint.Email;
import az.spring.bankapplication.constraint.UniquePinCode;
import az.spring.bankapplication.constraint.Username;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {

    @Email
    private String email;

    @Username
    private String username;

    @UniquePinCode
    private String pinCode;

    @NotNull
    private boolean emailVerified;

    @NotNull
    private Long fkAccountId;

}
